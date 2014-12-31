/**
 * DictEntry is java class that contains the information about the Dictionary entries.
 * 
 * @author Zaid Albirawi
 * Student #: 
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #4
 * 
 * @version 1.0 15/11/2013
 */

public class DictEntry 
{
	private Position p;
	private int color;
	
	public DictEntry (Position p, int color)
	{
		this.p=p;
		this.color=color;
	}

	public Position getPosition(){return p;}
	
	public int getColor(){return color;}
}
