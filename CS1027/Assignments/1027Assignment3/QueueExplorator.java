/**
 * QueueExplorator implements the interface Explorator and uses a queue to solve a given maze.
 * 
 * @author Zaid Albirawi
 * @version 1.0 20/03/2012
 */
public class QueueExplorator implements Explorator
{
	/**
	  * Creates a LinkedQueue of Square objects called queue
	  */
	private LinkedQueue<Square> queue;
	
	/**
	  * QueueExplorator is the constructor for the QueueExplorator class
	  */
	public QueueExplorator()
	{
		/**
		  * Initializes the LinkedQueue queue and enqueues the its first element
		  * which is the starting point in the maze.
		  */
		queue = new LinkedQueue<Square>();
		queue.enqueue(Maze.start);
	}
	
	/**
	  * A Method that clears the queue.
	  */
	public void clear()
	{
		queue.clear();
	}
	
	/**
	  * A Method that checks if the queue is empty.
	  * 
	  * @return			returns a boolean that provides the program with information
	  * 				on weather the queue is empty or not.
	  */
	public boolean isEmpty()
	{
		if (queue.isEmpty()) return true;
		return false;
	}
	
	/**
	  * A getter for the next object in the queue.
	  * 
	  * @return			returns the next Square object in the queue.
	  */
	public Square getNext()
	{
		return queue.dequeue();
	}
	
	/**
	  * A setter that adds a new Square object to the queue.
	  *
	  * @param		s			a Square Object that contains the information of either of the 
	  * 						start and finish squares.
	  */
	public void add(Square s)
	{
		queue.enqueue(s);
	}
}
