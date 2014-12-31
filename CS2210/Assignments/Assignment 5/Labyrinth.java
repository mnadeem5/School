/**
 * Labyrinth is a class that extracts a labyrinth from a text file, constructs it and save it into a Graph, and produces a 
 * solution for it.
 * 
 * @author Zaid Albirawi
 * Student #: 
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #5
 * 
 * @version 1.0 22/11/2013
 */

import java.io.*;
import java.util.*;


public class Labyrinth 
{
	
	/**
	 * Creates the instant variables graph, start, end, k, n and soluList. Those variables will contains, the graph database,
	 * the starting point of the labyrinth, the ending point of the labyrinth, the size of the output screen, the number walls
	 * that the program is allowed to break in its search for the solution, the number of nodes, and a List that will contain the bath for the 
	 * labyrinths solution, respectively.
	 */
	private Graph graph;
	private int start, end, k, w, n;
	private List<Node> soluList= new ArrayList<>();
	
	/**
	  * Labyrinth is the constructor of the class Labyrinth. It creates the Graph database that will contain the information of the
	  * labyrinth, it will read the contents of a text file that will contains the information about the labyrinth.
	  * 
	  * @param		inputFile		is a String that contains the location of the text file containing the  labyrinth's information.
	  * 
	  */
	public Labyrinth (String inputFile) throws LabyrinthException, GraphException
	{
		//Creates the variables w, l , node, str, holder.
		int l , node=0;
		String str;
		char holder;
		try
		{
			//Creates a BufferedReader, that will read from the text file.
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
			
			//Skips the line that contains the speed.
			in.readLine();
			//Assigns the integer value of the second line to w.
			w=Integer.valueOf(in.readLine());
			//Assigns the integer value of the third line to l.
			l=Integer.valueOf(in.readLine());
			//Assigns the integer value of the forth line to k.
			k=Integer.valueOf(in.readLine());
			n=l*w;
			
			//Initializes the value of graph, give it a length of the length x the width provided by the text file.
			graph = new Graph(n);
			
			//Iterates through the rest of the file line by line to insert the labyrinth's data into the Graph database.
			for (int i=0; i<2*l-1; i++)
			{
				//Initializes the value of str, to the newly read line.
				str=in.readLine();
				//Iterates through the line.
				for (int j=0; j<2*w-1 ;j++)
				{
					//initializes the value of the character holder to the char value of str at j.
					holder=str.charAt(j);
					//Checks if holder is equal to 's', if it is, increment the value of node and set the value of the intense value start to node.
 					if (holder=='s')start=node++;
					//Checks if holder is equal to 'e', if it is, increment the value of node and set the value of the intense value end to node.
					else if (holder=='e')end=node++;
					//Checks if holder is equal to 'o', if it is, increment the value of node.
					else if (holder=='o')node++;
					//Checks if holder is equal to 'h', if it is, it inserts an edge into graph, of a "horizontal wall" type.
					else if (holder=='h')graph.insertEdge(graph.getNode(node-1), graph.getNode(node), "horizontal wall");
 					//Checks if holder is equal to 'v', if it is, it inserts an edge into graph, of a "vertical wall" type.
					else if (holder=='v') graph.insertEdge(graph.getNode(node-w+((j+1)/2)), graph.getNode(node+((j+1)/2)), "vertical wall");
 					//Checks if holder is equal to '-', if it is, it inserts an edge into graph, of a "horizontal hall" type.
					else if (holder=='-') graph.insertEdge(graph.getNode(node-1), graph.getNode(node), "horizontal hall");
 					//Checks if holder is equal to '|', if it is, it inserts an edge into graph, of a "vertical hall" type.
					else if (holder=='|') graph.insertEdge(graph.getNode(node-w+((j+1)/2)), graph.getNode(node+((j+1)/2)), "vertical hall");
				}
			//Closes the inputStream.
			}in.close();
		//throws an exception if the file is not found.
		}catch (IOException e){throw new LabyrinthException("The file does not exist!");}
	}

	/**
	  * A getter method for the graph.
	  * 
	  * @return					returns Graph database that contains the labyrinth.
	  * 
	  */
	public Graph getGraph() throws LabyrinthException
	{
		//checks if the graph is initialized, if not, throws a LabyrinthException.
		if (graph==null) throw new LabyrinthException ("The graph is not defined!");
		//else, return graph.
		return graph;
	}
	
	/**
	  * Solve is a method that uses the help method solver to solve the labyrinth.
	  * 
	  * @return					an Iterator that contains a list that will contain the solution for the labyrinth.
	  * 
	  */
	public Iterator<Node> solve() throws GraphException 
	{
		//Creates a Node, start, that contains the the information about the stating position, adds it to the solution list, and marks it as visited.
		Node start = graph.getNode(this.start);soluList.add(start);start.setMark(true);
		//Calls the solver helper method.
		solver(graph.incidentEdges(start));
		//Checks if soluList contains a solution, if not, return null.
		if (soluList.size()==1)return null;
		//simplifies the solution.
		oneDegreeSimplifier();
		//else returns soluList.
		return soluList.iterator();
	}
	
	/**
	  * Solver is a helper method that alongside the helper method, possibleRoutes, solve the labyrinth.
	  * 
	  * @param		iter		an iterator that contains a list of Edges of possible routes.
	  * 
	  * @return					an Iterator that contains a list of Nodes that the method has visited.
	  * 3
	  */
	private List<Node> solver (Iterator<Edge> iter) throws GraphException
	{
		//Checks if there are no possible routes, if so, return null.
		if (iter==null) return null;
		else
		{
			//Keeps looping while there are possible routes.
			while (iter.hasNext())
			{
				//Denotes the start of the label next.
				next:{
					 //Creates the variables edge, n, str, check, that will contain the values of the next edge, second end point, 
					 //the Edge's type, and if the check was visited or not, respectively. 
					Edge edge=iter.next();Node n = edge.secondEndpoint();String str;boolean check=false;
					//Checks if the edge that is about to be processed is wall, if it is, check if it is allowed, it is not allowed
					//break to next, if allowed, precede.
					if ((str=edge.getType())=="vertical wall"||str=="horizontal wall") if (k<1) break next; else check=true;
					//Adds the second end point to the soluList, marks the Node as visited, if that node is from a wall edge, decrement k, and change the wall to a hall.
					soluList.add(n);n.setMark(true);
					if (check)
					{
						k--;
						if (str=="vertical wall")edge.setType("vertical hall");
						else edge.setType("horizontal hall");
					}
					//Checks if the end is reached, if it is, return soluList.
					if (n.getName()==end) return soluList;
					//If not, check there are any possible routes, using the helper method, possibleRoutes.
					else if (solver(possibleRoutes(graph.incidentEdges(n), edge.firstEndpoint()))==null) 
					{
						//If there are no more possible routes, retreat by remove the second end point node from the soluList, increment k if n is
						//wall type edge and rebuild the wall, reset mark to unvisited, and set the edges label to "Dead End!"
						soluList.remove(n);n.setMark(false);edge.setLabel("Dead End!");
						if (check)
						{
							k++;
							if (str=="vertical hall")edge.setType("vertical wall");
							else edge.setType("horizontal wall");
						}
					//Checks if the last value of the soluList is the end point, if it is, return soluList.
					}if (soluList.get(soluList.size()-1).getName()==end) return soluList;
				}
			}
		//If the solution was not found return null.
		}return null;
	}
	
	/**
	  * possibleRoutes is a helper method that supplies the solver method with an iterator of all the possible routes.
	  * 
	  * @param		parent		a Node that contains the information about the first end point of the edge.
	  * @param		iter		an iterator that contains a list of Edges of adjacent nodes to the node parent. 
	  * 
	  * @return					an Iterator that contains a list of possible route edges.
	  * 
	  */
	private Iterator<Edge> possibleRoutes(Iterator<Edge> iter, Node parent)
	{
		//Creates an ArrayList that will hold a list of the possible routes.
		List<Edge> possibleRoutes= new ArrayList<>();
		//Loops while there are still more adjacent edges.
		while (iter.hasNext())
		{
			//Creates and initializes edge, and node, that will hold the information of the next a adjacent node to parent, and second
			//end point of the edge, respectively.
			Edge edge = iter.next();
			//Checks the second end point, the destination of the edge, has already been visited, or if it is a dead end, if neither, 
			//add the edge to the possible routes list.
			if (!edge.secondEndpoint().getMark()&&edge.getLabel()!="Dead End!")possibleRoutes.add(edge);
		}
		//Checks if the list is empty, if it is not, return an iterator of the shuffled possibleRoutes list.
		if (!possibleRoutes.isEmpty()) return shuffle(possibleRoutes).iterator();
		//if it is empty, return null.
		return null;
	}
	
	/**
	  * A method that shuffles the order of the possibleRoutes.
	  * 
	  * @return					returns a list of shuffled possibleRoutes.
	  * 
	  */
	private List<Edge> shuffle(List<Edge> edges)
	{
		//Creates an int n, and an edge array that will hold the number of edges, and the edges, respectively.
		int n=edges.size();Edge [] array = new Edge[n];
		//A list that will hold the shuffled possibleRoutes 
		List<Edge> possibleRoutes= new ArrayList<>();
		
		//Fills the array with all the edges.
		for(int i=0; i<n; i++)array[i]=edges.get(i);
		
		//Creates a random variable r, and a new Random object, rand.
		int r;Random rand = new Random();
		//Runs until the possibleRoutes list contains all the edges.
		while(possibleRoutes.size()<n)
		{ 
			//Creates a new random number between 0-n
			r = rand.nextInt(n); 
			//Checks if the random number at the array exists.
			if (array[r]!=null)
			{
				//Adds the edge to the possibleRoutes list, and removes it from the array.
				possibleRoutes.add(array[r]);array[r]=null;
			}
		//returns the shuffled possibleRoutes list.
		}return possibleRoutes;
	}
	
	/**
	  * hasNode is a method that checks if the Node exists in the graph or not.
	  * 
	  * @param		u			is an integers that may refer to the name of an existing Node.
	  *
	  * @return					returns a boolean to wither the Node is in the graph or not.
	  * 
	  */
	private boolean hasNode(int u)
	{
		//Checks if a Node of the name u exist in the graph, if not, throws a GraphEception.
		if (u<0||u>n-1)return false;
		//returns the Node of the name u.
		return true;
	}
	
	
	/**
	  * oneDegreeSimplifier is a helper method that edits the final solution to find shortcuts from a node to another. 
	  * 
	  */
	private void oneDegreeSimplifier() throws GraphException
	{
		//Creates a list that will contain the modified solution.
		List<Node> mode = new ArrayList<>();
		//Creates an iterator for the soluList
		Iterator<Node> iter = soluList.iterator();
		//Creates node that will used for some tests
		Node u , v1, v2;
		//Will contain the type of edges.
		String str;
		//Will contain the name of the u Node
		int uName;
		//Sets v1 to the start node of the soluList and adds it to the modified list.
		v1 = soluList.get(0); mode.add(v1);
		//Iterates while iter has next
		while (iter.hasNext())
		{
			next:
			{
				//Creates a short term that will contain a list of the nodes that are adjacent to u.
				List<Node> test = new ArrayList<>();
				//sets you to next node in the iterator.
				u = iter.next();
				uName = u.getName();
				
				//Skips to the node if the index value of u in the lower than the index value of the node stored at the
				//end of the modified list.
				if (soluList.indexOf(u)<soluList.indexOf(mode.get(mode.size()-1))) break next;
				
				//left: Checks if the node to the left of u exists in the graph, checks if the soluList contains that node,
				//and checks if this node does not equal to the node that was just tested.
				if (hasNode(uName-1)&&soluList.contains(v2=graph.getNode(uName-1))&&v1!=v2)
				{
					//Checks if the node u and v2 contain an edge between them, if they do, then make sure that its not a wall. Adds the node to the test list.
					if (graph.areAdjacent(u, v2)&&((str=graph.getEdge(u, v2).getType())=="vertical hall"||str=="horizontal hall"))test.add(graph.getNode(uName-1));
				}
				//right: Checks if the node to the right of u exists in the graph, checks if the soluList contains that node,
				//and checks if this node does not equal to the node that was just tested.
				if (hasNode(uName+1)&&soluList.contains(v2=graph.getNode(uName+1))&&v1!=v2)
				{
					//Checks if the node u and v2 contain an edge between them, if they do, then make sure that its not a wall. Adds the node to the test list.
					if (graph.areAdjacent(u, v2)&&((str=graph.getEdge(u, v2).getType())=="vertical hall"||str=="horizontal hall"))test.add(graph.getNode(uName+1));
				}
				//up: Checks if the node above u exists in the graph, checks if the soluList contains that node,
				//and checks if this node does not equal to the node that was just tested.
				if (hasNode(uName-w)&&soluList.contains(v2=graph.getNode(uName-w))&&v1!=v2)
				{
					//Checks if the node u and v2 contain an edge between them, if they do, then make sure that its not a wall. Adds the node to the test list.
					if (graph.areAdjacent(u, v2)&&((str=graph.getEdge(u, v2).getType())=="vertical hall"||str=="horizontal hall"))test.add(graph.getNode(uName-w));
				}
				//bottom: Checks if the node at the bottom of u exists in the graph, checks if the soluList contains that node,
				//and checks if this node does not equal to the node that was just tested.
				if (hasNode(uName+w)&&soluList.contains(v2=graph.getNode(uName+w))&&v1!=v2)
				{
					//Checks if the node u and v2 contain an edge between them, if they do, then make sure that its not a wall. Adds the node to the test list.
					if (graph.areAdjacent(u, v2)&&((str=graph.getEdge(u, v2).getType())=="vertical hall"||str=="horizontal hall"))test.add(graph.getNode(uName+w));
				}
				
				//Since test list size can only be 1 or 2
				//Checks if the size is 2.
				if (test.size()==2) 
				{
					//Stores the values of the first and second values from the list into , v1, and v2 respectively 
					v1=test.get(0);v2=test.get(1);
					//Checks which one comes after the other in the soluList order, and picks the one that is larger, which mean that itll take a shorcut
					if(soluList.indexOf(v1)>soluList.indexOf(v2))mode.add(test.get(0));
					else mode.add(test.get(1));
				}
				//If there is only one value then add that to the list.
				else if (test.size()==1)mode.add(test.get(0));
				//If the node that was just add to mode is equal to the last node of the soluList, stop.
				if (test.get(test.size()-1).getName()==end)break;
				//else keep going and set v1 to u, which makes it the node that was just checked.
				v1=u;
			}
		}
		//Changes the value of soluList to the new modified list.
		soluList=mode;
	}
}