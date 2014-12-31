/**
 * Node is java class that will be used to store information about the nodes of the graph.
 * 
 * @author Zaid Albirawi
 * Student #: 
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #5
 * 
 * @version 1.0 22/11/2013
 */
public class Node 
{
	//creates private integer and boolean that will store the name of the node and the mark of the node, respectively.
	private int name;
	private boolean mark;
	
	//The constructor for the class, initializes the value of the variable name.
	public Node (int name){this.name = name;}
	
	//A setter for the variable mark.
	public void setMark (boolean mark){this.mark=mark;}
	
	//A getter for the variable mark.
	public boolean getMark(){return mark;}
	
	//A getter for the variable name.
	public int getName(){return name;}
}
