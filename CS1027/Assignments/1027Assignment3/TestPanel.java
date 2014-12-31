/**
 * TestPanel tests the functionality of the class MazeFrame.
 * 
 * @author Zaid Albirawi
 * @version 1.0 20/03/2012
 */

public class TestPanel
{
  public static void main(String[] args)
  {
	/**
	 * Creates a maze of the Maze class to test the functionality of the MazeFrame class.
	 */
    Maze maze = new Maze("maze1.txt");
    MazeFrame frame = new MazeFrame(maze);
  }
}