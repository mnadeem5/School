/**
 * QueueExplorator implements the interface Explorator and uses a stack to solve a given maze.
 * 
 * @author Zaid Albirawi
 * @version 1.0 20/03/2012
 */
public class StackExplorator implements Explorator
{
	/**
	  * Creates a LinkedStack of Square objects called stack
	  */
	private LinkedStack<Square> stack;
	
	/**
	  * StackExplorator is the constructor for the StackExplorator class
	  */
	public StackExplorator()
	{
		/**
		  * Initializes the LinkedStack stack and pushes the its first element
		  * which is the starting point in the maze.
		  */
		stack = new LinkedStack<Square>();
		stack.push(Maze.start);
	}
	
	/**
	  * A Method that clears the stack.
	  */
	public void clear()
	{
		stack.clear();
	}
	
	/**
	  * A Method that checks if the stack is empty.
	  * 
	  * @return			returns a boolean that provides the program with information
	  * 				on weather the stack is empty or not.
	  */
	public boolean isEmpty()
	{
		if (stack.isEmpty())return true;
		else return false;
	}
	
	/**
	  * A getter for the next object in the stack.
	  * 
	  * @return			returns the next Square object in the stack.
	  */
	public Square getNext()
	{
		return stack.pop();
	}
	
	/**
	  * A setter that adds a new Square object to the stack.
	  *
	  * @param		s			a Square Object that contains the information of either of the 
	  * 						start and finish squares.
	  */
	public void add (Square s)
	{
		this.stack.push(s);
	}
}
