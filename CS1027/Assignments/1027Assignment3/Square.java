/**
 * Square is a class will contain the x, y information of the squares in a maze of the class Maze
 * 
 * @author Zaid Albirawi
 * @version 1.0 20/03/2012
 */

public class Square 
{
	private int i, j;
	
	/**
	  * The constructor of the class Square that takes in two parameters and sets the values of the
	  * x and y position of the square to these points.
	  * 
	  * @param		j		an Integer that contains the y component of square positing point.
	  * @param		i		an Integer that contains the x component of square positing point.
	  */
	public Square (int j, int i)
	{
		this.j=j;
		this.i=i;
	}
	
	/**
	  * A getter for the y value of the square position.
	  * 
	  * @return			returns an Integer that holds the value of the y position of the square.
	  */
	public Integer getJ()
	{
		return this.j;
	}
	
	/**
	  * A getter for the x value of the square position.
	  * 
	  * @return			returns an Integer that holds the value of the x position of the square.
	  */
	public Integer getI()
	{
		return this.i;
	}
}
