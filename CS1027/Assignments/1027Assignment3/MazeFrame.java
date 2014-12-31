import java.awt.*;
import javax.swing.*;

public class MazeFrame extends JFrame{
  final static Color BLACK = new Color(0, 0, 0);
  final static Color WHITE = new Color(255, 255, 255);
  final static Color RED = new Color(255, 0, 0);
  final static Color GREEN = new Color(0, 255, 0);
  final static Color BLUE = new Color(0, 0, 255);

  /**
   * Creates a new panel using the MazePanel class.
   */
  private MazePanel panel;
  
  /**
   * Creates an empty boolean array that will help draw the empty maze.
   */
  private boolean [][] emptyMazeAry;
  
  /**
   * The constructor of the class MazeFrame that takes in a maze as a parameter and provides a
   * visual solution to it using the MazePanel class.
   * 
   * @param		maze			a Maze object that represents the maze the program is solving. 
   */
  MazeFrame(Maze maze)
  {
    super("Maze");  
    
    /**
     * Initializes the height and width variables.
     */
    int h = maze.getHeight();
    int w = maze.getWidth();
    
    /**
     * Initializes panel from the MazePanel class giving it a height and a width.
     */
    panel = new MazePanel(h, w);  
    add(panel);  
    
    /**
     * Gives the emptyMazeAry a value.
     */
    emptyMazeAry=maze.getEmptyAry();
    
    /**
     * Adds the maze's white spaces by adding objects the gQueue CircularArrayQueue in 
     * the MazePanel class.
     */
    for (int j=0; j<h; j++)
    {
    	for (int i=0; i<w; i++)
    	{
    		if (emptyMazeAry[j][i]==true)
    		{
    			panel.add(new SquareObject(WHITE,j,i));
    		}
    	}
    }
    /**
     * Adds the maze's start and finish circles by adding objects the gQueue CircularArrayQueue in 
     * the MazePanel class.
     */
    panel.add(new CircleObject(GREEN, maze.getStart().getJ(), maze.getStart().getI()));
    panel.add(new CircleObject(BLUE, maze.getFinish().getJ(), maze.getFinish().getI()));
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    pack();
    setResizable(false);
    setVisible(true);
    repaint();
  }
  
  /**
   * A method that adds all the GraphicalObject triangles to the gQueue CircularArrayQueuein 
   * the MazePanel class.
   *
   * @param		obj			a GraphicalObject object that represents a triangle shape. 
   */
  void add(GraphicalObject obj)
  {
	  panel.add(obj);
	  /**
	    * Repaints the panel triggering the paintComponent method and slows the drawing operation down.
	    */
	  repaint();pause(100);
  }
  
  /**
   * A method that is used to delay or slow down the drawing operation.
   * 
   *  @param		i			an Integer object that represents the duration of the delay.
   */
  static void pause (int i) 
  {
	  try 
	  {
		  Thread.sleep(i);
	  }catch (InterruptedException e) { }
  }
}