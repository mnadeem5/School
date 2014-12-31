/**
 * Maze is a class that, along with the classes StackExplorator, QueueExplorator, MazePanel and MazeFrame,
 * solves and provides a visual solution to a gives maze.
 * 
 * @author Zaid Albirawi
 * @version 1.0 20/03/2012
 */

import java.awt.*;
public class Maze 
{
	/**
     * Creates two integers x and y that represent the height and the width of the maze respectively.
     */
	private int x, y;
	/**
     * Creates two Square objects start and finish that will hold the information for the maze's start
     * and finish points.
     */
	public static Square start;
	public Square finish;
	/**
     * Creates a boolean that will help determine when to stop the while loop that will be used to solve
     * the maze.
     */
	private boolean result;
	/**
     * Creates two 2D boolean arrays that will hold information about the maze.
     * blnMazeArray will be used to determine the available squares in the maze.
     * blnEmptyAry will help draw the array before the program starts solving it.
     */
	private boolean [][] blnMazeArray;
	private boolean [][] blnEmptyAry;
	/**
     * Creates a red color that will be used to color the direction triangles in the explore method. 
     */
	final static Color RED = new Color(255, 0, 0);
	/**
     * Creates a MazeFrame object that will provide the visual solution to the maze.
     */
	private MazeFrame frame;
	
	/**
	  * The constructor of the class Maze that reads the document that contains the maze's information,
	  * that is referenced by the fileName parameter, and adds those line to a LinkedQueue object, docMazeQueue.
	  * The information stored in the docMazeQueue queue will then be used in initializes all the objects needed
	  * to solve the maze.
	  * 
	  * @param		fileName		a String that contains a reference to the location of the maze information 
	  * 							document.
	  */
	public Maze(String fileName)
	{
		/**
		  * Initializes the LinkedQueue docMazeQueue.
		  */
		LinkedQueue<String> docMazeQueue = new LinkedQueue<String> ();
		
		InStringFile reader = new InStringFile(fileName);
		System.out.println("\nReading from file " + fileName + "\n");
		
		/**
		  * Reads the document and enqueues all the lines in the docMazeQueue queue.
		  */
		do
	    {
			docMazeQueue.enqueue(reader.read());
	    }
		while (!reader.endOfFile()); 	   
		reader.close();
		
		/**
		  * Initializes the values of the Integers x, y.
		  */
		y=Integer.parseInt(docMazeQueue.dequeue());
		x=Integer.parseInt(docMazeQueue.dequeue());
		/**
		  * Initializes the values of the Squares start and finish.
		  */
		Maze.start = new Square(Integer.parseInt(docMazeQueue.dequeue()), Integer.parseInt(docMazeQueue.dequeue()));
		finish = new Square(Integer.parseInt(docMazeQueue.dequeue()), Integer.parseInt(docMazeQueue.dequeue()));
		/**
		  * Initializes the boolean arrays blnMazeArray and blnEmptyAry.
		  */
		blnMazeArray=new boolean[y][x];
		blnEmptyAry=new boolean[y][x];
		
		/**
		  * Creates a String that will hold the value of the dequeued docMazeQueue string.
		  */
		String mazeLine;
		/**
		  * A while loop that will help inserting values in the boolean arrays.
		  */
		while (!docMazeQueue.isEmpty())
		{
			for (int j=0; j<this.y; j++)
			{
				/**
				  * Initializes the value of mazeLine and gives it the value of the docMazeQueue dequeued string.
				  */
				mazeLine=docMazeQueue.dequeue();
				if (mazeLine.length()==x)
				{
					for (int i=0; i<x;i++)
					{
						/**
						  * Sets the values of the arrays to false if the character is 0 and true if the character
						  * us 1.
						  */
						if (mazeLine.charAt(i)=='0')
						{
							this.blnMazeArray[j][i]=false;
							this.blnEmptyAry[j][i]=false;
						}
						else if (mazeLine.charAt(i)=='1')
						{
							this.blnMazeArray[j][i]=true;
							this.blnEmptyAry[j][i]=true;
						}
						/**
						  * Error message if the informations is corrupted or invalid.
						  */
						else System.out.println("WARNING: the maze input at ("+j+", "+i+"is invalid");
					}
				}
				else 
				{
					/**
					  * Error message if the informations is corrupted or invalid.
					  */
					System.out.println("WARNING: Maze horizontal length error!");
				}
			}
		}
		/**
		  * Sets the value of the square that contains the start point to false.
		  */
		this.blnMazeArray[start.getJ()][start.getI()]=false;
	}
	
	/**
	  * A method that will explore and solve the maze using either the StackExplorator or the QueueExplorator
	  * and calls the MazeFrame class to provide a visual solution.
	  * 
	  * @param		e				an Explorator object that can be either a stack or a queue.
	  */
	public boolean explore(Explorator e)
	{
		/**
		  * Initializes the MazeFrame maze.
		  */
		frame=new MazeFrame(this);
		
		/**
		  * A while loop that solves the maze using the provided Explorator object.
		  */
		while (!result)
		{
			Square s = e.getNext();
			int i = s.getI(), j = s.getJ();
			/**
			  * An if statement that determines if the finish square is reached.
			  */
			if (j==finish.getJ()&&i==finish.getI())
			{
				result=true;
			}
			
			/**
			  * Four if statements that determines which squares to add to the Explorator and
			  * adds there corresponding GraphicalObject triangle to the frame. 
			  */
			if (j>0&&this.blnMazeArray[j-1][i]==true)
			{
				e.add(new Square (j-1,i));
				this.blnMazeArray[j-1][i]=false;
				frame.add(new TriangleUpObject(RED, j-1, i));
			}
			if (i>0&&this.blnMazeArray[j][i-1]==true)
			{
				e.add(new Square (j,i-1));
				this.blnMazeArray[j][i-1]=false;
				frame.add(new TriangleLeftObject(RED, j, i-1));
			}
			if (i<this.x-1&&this.blnMazeArray[j][i+1]==true)
			{
				e.add(new Square (j,i+1));
				this.blnMazeArray[j][i+1]=false;
				frame.add(new TriangleRightObject(RED, j, i+1));
			}
			if (j<this.y-1&&this.blnMazeArray[j+1][i]==true)
			{
				e.add(new Square (j+1,i));
				this.blnMazeArray[j+1][i]=false;
				frame.add(new TriangleDownObject(RED, j+1, i));
			}
			
			/**
			  * Checks if the Explorator is empty and stops the loop. If this statement is triggered then
			  * the maze does not have a solution.
			  */
			if (e.isEmpty()) return false;
		}
		return result;
	}
	
	/**
	  * A getter for the height of the maze.
	  * 
	  * @return			returns an integer that holds the value of the maze's height.
	  */
	public Integer getHeight()
	{
		return y;
	}
	
	/**
	  * A getter for the width of the maze.
	  * 
	  * @return			returns an integer that holds the value of the maze's width.
	  */
	public Integer getWidth()
	{
		return x;
	}
	
	/**
	  * A getter for the blnEmptyAry array.
	  * 
	  * @return			returns a boolean array that holds the information that constructs the 
	  * 				maze, visually.
	  */
	public boolean [][] getEmptyAry()
	{
		return blnEmptyAry;
	}
	
	/**
	  * A getter for the start point of the maze.
	  * 
	  * @return			returns a Square that holds the value of the maze's start point.
	  */
	public Square getStart()
	{
		return start;
	}
	
	/**
	  * A getter for the finish point of the maze.
	  * 
	  * @return			returns a Square that holds the value of the maze's finish point.
	  */
	public Square getFinish()
	{
		return finish;
	}
}
