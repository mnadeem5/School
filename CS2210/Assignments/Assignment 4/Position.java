/**
 * Position is a java class that contains the x and y values of position on an xy grid.
 * 
 * @author Zaid Albirawi
 * Student #: 
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #4
 * 
 * @version 1.0 15/11/2013
 */
public class Position 
{
	private int x, y;
	
	public Position (int x, int y)
	{
		this.x=x;this.y=y;
	}
	
	public int getX(){return x;}
	
	public int getY(){return y;}
	
	/**
	  * The method compareTo compares two positions, this position and  another position provided by the user, and determines
	  * if this position's value is greater, lower, or equal to the provided position, using the row order comparison.
	  * 
	  * @param		p			a position that will be compared to this position.
	  * 
	  * @return					return 1, -1 or 0. 1 if the compared position's value is less than this position, 
	  * 						-1 when it is larger, and 0 if they are equal.
	  */
	public int compareTo(Position p)
	{
		if (this.getY()>p.getY())return 1;
		else if (this.getY()<p.getY())return -1;
		else
		{
			if (this.getX()>p.getX())return 1;
			else if (this.getX()<p.getX())return -1;
			else return 0;
		}
	}	
}