/**
 * Graph is a java class that implements GraphADT, this class uses the Node and Edge classes to construct a graph database.
 * 
 * @author Zaid Albirawi
 * Student #: 
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #5
 * 
 * @version 1.0 22/11/2013
 */

import java.util.*;

public class Graph implements GraphADT 
{
	/**
	 * Creates the instant variables graph, edges and n that will contain the a Nodes array, and Edge matrix of adjacency, and an 
	 * integer that will contain length of the Nodes array, respectively.
	 */
	private Node [] graph;
	private Edge [][] edges;
	private int n;
	
	/**
	  * Graph is the constructor of the class Graph. It initializes the values of n, graph, and edges, and gives n and graph values. 
	  * 
	  * @param		n			is an integer that will contain the length of the graph, the number of Nodes.
	  * 
	  */
	public Graph (int n)
	{
		this.n=n;
		graph = new Node [n];
		for (int i=0; i<n; i++) graph[i]=new Node(i);
		edges = new Edge [n][n];
	}
	
	/**
	  * insertEdge is a method that will insert an Edge into the adjacency matrix defined by edges.
	  * 
	  * @param		u			is a Node that contains the information of the first end point of the edge.
	  * @param		v			is a Node that contains the information of the second end point of the edge.
	  * @param		type		is a String that contains the information of the type of the edge.
	  * 
	  */
	public void insertEdge(Node u, Node v, String type) throws GraphException 
	{
		//Creates and initializes the values of uName and vName.
		int uName=u.getName(), vName=v.getName();
		//Checks if uName and vName exist in the graph, if not, throws a GraphEception.
		if (uName<0||uName>n-1||vName<0||vName>n-1) throw new GraphException("Insertion failed, Node does not exist!");
		//Checks if the edge at uName and vName exits in the graph, if so, throws a GraphException.
		if (edges[uName][vName]!=null)throw new GraphException("The Edge connecting u and v already exists.");
		//Adds the edge to the adjacency matrix.
		edges[uName][vName]=new Edge(u, v, type);
		edges[vName][uName]=new Edge(v, u, type);
	}

	/**
	  * getNode is a getter method for a Node in the graph.
	  * 
	  * @param		u			is an integers that may refer to the name of an existing Node.
	  *
	  * @return					returns a Node with name of u, else produces a GraphException.
	  * 
	  */
	public Node getNode(int u) throws GraphException 
	{
		//Checks if a Node of the name u exist in the graph, if not, throws a GraphEception.
		if (u<0||u>n-1)throw new GraphException("Node does not exist!");
		//returns the Node of the name u.
		return graph[u];
	}
	
	/**
	  * incidentEdges is a method that will return an iterator with all the incident edges for a Node.
	  * 
	  * @param		u			is the Node that the test will be conducted on.
	  *
	  * @return					returns an Iterator with all the incident edges with u.
	  * 
	  */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException 
	{
		//Creates an integer uName that will hold the name of u.
		int uName=u.getName();
		//Checks if u exist in the graph, if not, throws a GraphEception.
		if (uName<0||uName>n-1)throw new GraphException("Node does not exist!");
		
		//Creates an ArrayList, edgeList, that will hold the list of possible edges that connect with Node u.
		List<Edge> edgeList= new ArrayList<>();	
		//Iterates throw the adjacency matrix, checks if there are any incident edges for u.
		for (int i=0; i<n; i++)if (edges[uName][i]!=null) edgeList.add(edges[uName][i]);
		//Checks if edgeList is empty, if it is not, return an iterator of edgeList.
		if (!edgeList.isEmpty())return edgeList.iterator();
		//If it is empty, return null.
		return null;
	}

	/**
	  * getEdge is a getter for edges in Graph.
	  * 
	  * @param		u			is a Node that contains the information of the first end point of the edge.
	  * @param		v			is a Node that contains the information of the second end point of the edge.
	  *
	  * @return					returns the edge connecting Nodes u and v.
	  * 
	  */
	public Edge getEdge(Node u, Node v) throws GraphException 
	{
		//Creates and initializes the values of uName and vName.
		int uName=u.getName(), vName=v.getName();
		//Checks if either Nodes u or v do not exist, if one or both don't exist, throws a GraphException.
		if (uName<0||uName>n-1||vName<0||vName>n-1)throw new GraphException("Nodes u and/or v do not exist!");
		//Checks if there is an Edge connecting u and v, if not, throws a GraphException.
		if (!areAdjacent(u, v))throw new GraphException("There is no edge between u and v.");
		//returns the edge connecting u an v.
		return edges[uName][vName];
	}

	/**
	  * areAdjacent 
	  * 
	  * @param		u			is a Node that contains the information of the first end point of the edge.
	  * @param		v			is a Node that contains the information of the second end point of the edge.
	  *
	  * @return					returns a boolean to wither the nodes are adjacent or not.
	  * 
	  */
	public boolean areAdjacent(Node u, Node v) throws GraphException 
	{
		//Creates and initializes the values of uName and vName.
		int uName=u.getName(), vName=v.getName();
		//Checks if either Nodes u or v do not exist, if one or both don't exist, throws a GraphException.
		if (uName<0||uName>n-1||vName<0||vName>n-1)throw new GraphException("Nodes u and/or v do not exist!");
		//Checks if there exist an edge connecting u and v, if not, return false.
		if (edges[uName][vName]==null)return false;
		//return true;
		return true;
	}
}
