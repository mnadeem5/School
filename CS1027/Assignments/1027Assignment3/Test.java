/**
 * TestPanel tests the functionality of the class Maze and the Explorator method.
 * 
 * @author Zaid Albirawi
 * @version 1.0 20/03/2012
 */

public class Test 
{
	public static void main(String args[])
	{
		/**
		 * Creates a maze of the Maze class to test the functionality of the Maze class and the
		 * Explorator method using both StackExplorator and QueueExplorator.
		 */
	    Maze maze = new Maze("D:\\Workspace\\1027Assignment3\\src\\maze1.txt");
	    Explorator q = new QueueExplorator();
	    maze.explore(q);
	    Maze maze1 = new Maze("D:\\Workspace\\1027Assignment3\\src\\maze2.txt");
	    Explorator s = new StackExplorator();
	    maze1.explore(s);
	  }
}
