import java.awt.*;
import java.awt.event.PaintEvent;
import javax.swing.*;

public class MazePanel extends JPanel  
{
  final int SIZE = 20;
  final Color BLACK = new Color(0, 0, 0);

  private CircularArrayQueue<GraphicalObject> gQueue;
  
  private GraphicalObject gObj;
  
  /**
   * A method that adds all the GraphicalObject objects to the
   * CircularArrayQueue gQueue
   *
   * @param		gObj		a GraphicalObject that is enqueued to draw the maze. 								
   */
  void add(GraphicalObject obj)
  {
	  gQueue.enqueue(obj);
  }
  
  /**
   * A constructor for the MazePanel class that initializes the gQueue,
   * and sets the size and background of the panel.
   *
   * @param		h			an Integer that represents the height of the panel. 
   * @param		w			an Integer that represents the width of the panel. 
   */
  MazePanel(int h, int w)
  {
	  gQueue = new CircularArrayQueue<GraphicalObject>();
	  
	  Dimension g = new Dimension(SIZE*w, SIZE*h);
	  setPreferredSize(g);
	  setBackground(BLACK);
  }

  /**
   * A method that is activated when the repaint() is called.
   *
   * @param		g			a Graphics object that represents the panel. 
   */
  public void paintComponent(Graphics g) 
  {
	  super.paintComponent(g);
	  int i=0;
	  
	  /**
	   * A while loop that draws the maze. The gQueue is dequeued and enqueued to insure that no
	   * graphics are missing. The i int controls the while from going on infinitely. 
	   */
	  while (i<gQueue.size())
	  {
		  gObj=gQueue.dequeue();
		  gQueue.enqueue(gObj);
		  gObj.draw(g, SIZE);i++;
	  }
  }
}