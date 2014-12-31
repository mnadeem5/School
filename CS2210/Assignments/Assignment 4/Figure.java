/**
 * 
 * @author Zaid Albirawi
 * Student #: 
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #4
 * 
 * @version 1.0 15/11/2013
 */
public class Figure implements FigureADT
{
	private int id, width, height, type;
	private Position pos;
	private BinarySearchTree bst=new BinarySearchTree();
	public Figure (int id, int width, int height, int type, Position pos)
	{
		this.id=id;
		this.width=width;
		this.height=height;
		this.type=type;
		this.pos=pos;
	}
	public void setType(int t) {type=t;}

	public int getWidth() {return width;}

	public int getHeight() {return height;}

	public int getType() {return type;}

	public int getId() {return id;}

	public Position getOffset() {return pos;}

	public void setOffset(Position value) {pos=value;}

	public void addPixel(int x, int y, int color) throws BSTException 
	{
		bst.insert(new DictEntry (new Position(x, y), color));
	}
	
	/**
	  * The method intersects compares the pixels of two figures and determine if the intersect at any points, or not.
	  * 
	  * @param		fig			a Figure that will be compared to the current figure for an intersection points.
	  * 
	  * @return					returns a true if the figures intersect and false if they don't.
	  */
	public boolean intersects(Figure fig) 
	{
		BinarySearchTree thisFig=this.bst, otherFig=fig.bst;
		Position thisPos=thisFig.smallest().getPosition(), otherPos=otherFig.smallest().getPosition();
		Position thisLargest=thisFig.largest().getPosition(), otherLargest=otherFig.largest().getPosition();
		Position thisOffPos, otherOffPos, thisOff, otherOff;
		int x, y, check;
		while (true)
		{
			//determine the value of the pixel of this figure + the offset
			thisOff=this.getOffset();
			x=thisPos.getX()+thisOff.getX();
			y=thisPos.getY()+thisOff.getY();
			thisOffPos=new Position (x, y);
			
			//determine the value of the pixel of the other figure + the offset
			otherOff=fig.getOffset();
			x=otherPos.getX()+otherOff.getX();
			y=otherPos.getY()+otherOff.getY();
			otherOffPos=new Position (x, y);
			
			check = thisOffPos.compareTo(otherOffPos);
			
			//checks if which position is lower and increments the lower one. 
			if (check==1)
			{
				//returns false if other figures runs out of pixels
				if (otherPos.compareTo(otherLargest)==0)
					return false;
				//increments the position
				else otherPos=otherFig.successor(otherPos).getPosition();
			}
			else if(check==-1)
			{
				//returns false if other figures runs out of pixels
				if (thisPos.compareTo(thisLargest)==0)
					return false;
				//increments the position
				else thisPos=thisFig.successor(thisPos).getPosition();
			}
			//returns true if check doesn't equal 1 or -1 therefore 0.
			else return true;
		}
	}
}