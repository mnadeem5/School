/**
 * Edge is java class that will contain the information about the edges of the graph, this class will contain the values of the
 * first and second end points of the edge, the edge's type and any labels for that edge.
 * 
 * @author Zaid Albirawi
 * Student #: 
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #5
 * 
 * @version 1.0 22/11/2013
 */
public class Edge 
{
	/**
	 * Creates the instance variables label, type u, v that will hold the values of the label, the type and first end point and 
	 * second end point of the edge, respectively.
	 */
	private String label="", type;
	private Node u, v;
	
	//The constructor for the class, initializes the values of the first end point, the second end point, and the type of the edge.
	public Edge (Node u, Node v, String type)
	{
		this.u=u;
		this.v=v;
		this.type=type;
	}
	
	//A getter for the first end point node.
	public Node firstEndpoint(){return u;}
	
	//A getter for the second end point node.
	public Node secondEndpoint(){return v;}
	
	//A getter for the type of the edge.
	public String getType (){return type;}
	
	//A setter for the type if the edge.
	public void setType(String type) {this.type=type;}
	
	//A setter for the edge's label.
	public void setLabel(String label){this.label=label;}
	
	//A getter for the edge's label.
	public String getLabel(){return label;}
}
