/**
 * BinarySearchTree is tree constructed from BinarySearchTreeNodes that are linked together to store DictEntry values
 * in an order manner. Every node will contain a root that can have a right and left values, where the value of the right
 * is larger and left value is smaller, than the roots value.
 * 
 * @author Zaid Albirawi
 * Student #: 
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #4
 * 
 * @version 1.0 15/11/2013
 */
public class BinarySearchTree implements BinarySearchTreeADT
{
	private BinarySearchTreeNode root;
	
	public BinarySearchTree()  
	{
		root=new BinarySearchTreeNode(null);
	}
	
	/**
	  * The method find uses the method findRoot to find a value that is stored in the BST.
	  * 
	  * @param		p			is a Position that contains the position of the entry we are trying to find. 
	  * 
	  * @return					returns a DictEntry that contains the the value of p.
	  */
	public DictEntry find (Position p)
	{
		BinarySearchTreeNode r= findRoot(p);
		if (r.getDictEntry()!=null)return r.getDictEntry();
		return null;
	}
	
	/**
	  * The method findRoot iterates through the tree to find the position of the value required.
	  * 
	  * @param		p			is a Position that contains the position of the entry we are trying to find. 
	  * 
	  * @return					returns a BinarySearchTreeNode that is the position p.
	  */
	private BinarySearchTreeNode findRoot (Position p)
	{
		BinarySearchTreeNode r = root;
		int check;
		while (r.getDictEntry()!=null)
		{
			check=r.getDictEntry().getPosition().compareTo(p);
			if (check==0)return r;
			//iterates through the BST depending on the value of compareTo method.
			else if (check==1)r=r.getLeft();
			else if (check==-1)r=r.getRight();
		}
		return r;
	}

	/**
	  * The method insert uses the method findRoot to find where value should be inserted and inserts it.
	  * 
	  * @param		data		is a DictEntry value that contains the data values that you want to add to the BST.
	  */
	public void insert(DictEntry data) throws BSTException 
	{
		Position p = data.getPosition();
		if (find(p)!=null) throw new BSTException ("Error: This key already exists!");
		else findRoot(p).setRoot(data);
	}
	
	/**
	  * The method remove removes a DictEntry at a specific position form the BST.
	  * 
	  * @param		p			is a Position that contains the position of the entry we are trying to remove. 
	  */
	public void remove(Position key) throws BSTException 
	{
		if (find(key)==null) throw new BSTException ("Error: This key does not exist!");
		else
		{
			BinarySearchTreeNode r=findRoot(key),  rRight=r.getRight(), rLeft=r.getLeft();
			DictEntry entry;
			
			//case 1: no children
			if (rRight.getDictEntry()==null&&rLeft.getDictEntry()==null)
			{
				//sets the right and left nodes to null, and then sets the root value to null;
				r.nullLeft();r.nullRight();
				r.setRoot(null);
			}
			//case 2: 1 child (right child)
			else if (rRight.getDictEntry()!=null&&rLeft.getDictEntry()==null)
			{
				/*
				saves the right child's value, and calls on the remove method to remove the right node. Then it sets 
				the root's value to that saved entry.
				*/
				entry=rRight.getDictEntry();
				remove(entry.getPosition());
				r.setRoot(entry);
			}
			//case 3: 1 child (left child)
			else if (rRight.getDictEntry()==null&&rLeft.getDictEntry()!=null)
			{
				/*
				saves the left child's value, and calls on the remove method to remove the left node. Then it sets 
				the root's value to that saved entry.
				*/
				entry=rLeft.getDictEntry();
				remove(entry.getPosition());
				r.setRoot(entry);
			}
			//case 3: 2 children
			else
			{
				/*
				saves the  successor's value, and calls on the remove method to remove the node that contains the successor's 
				value. Then it sets the root's value to that saved entry.
				*/
				entry=successor(r.getDictEntry().getPosition());
				remove(entry.getPosition());
				r.setRoot(entry);
			}
		}
	}
	
	/**
	  * The method successor method returns the successor of a specific position if it exists.
	  * 
	  * @param		p			is a Position that contains the position of the entry we are trying to find the successor for. 
	  * 
	  * @return					returns a DictEntry that contains the the value of p's successor.
	  */
	public DictEntry successor(Position key) 
	{
		BinarySearchTreeNode r = root, rParent=new BinarySearchTreeNode(null);
		DictEntry entry=r.getDictEntry();
		int check;
		while (entry!=null)
		{
			check=entry.getPosition().compareTo(key);
			//iterates through the BST and tries to find a successor
			if (check==1)
			{
				rParent=r;
				r=r.getLeft();
			}
			else r=r.getRight();
			entry=r.getDictEntry();
		}
		return rParent.getDictEntry();
	}

	/**
	  * The method predecessor method returns the predecessor of a specific position if it exists.
	  * 
	  * @param		p			is a Position that contains the position of the entry we are trying to find the predecessor for. 
	  * 
	  * @return					returns a DictEntry that contains the the value of p's predecessor.
	  */
	public DictEntry predecessor(Position key) 
	{
		BinarySearchTreeNode r = root, rParent=new BinarySearchTreeNode(null);
		DictEntry entry=r.getDictEntry();
		int check;
		while (entry!=null)
		{
			check=entry.getPosition().compareTo(key);
			//iterates through the BST and tries to find a predecessor
			if (check==-1)
			{
				rParent=r;
				r=r.getRight();
			}
			else r=r.getLeft();
			entry=r.getDictEntry();
		}
		return rParent.getDictEntry();
	}
	
	/**
	  * The method smallest method returns the smallest value in the BST.
	  *  
	  * @return					returns a DictEntry that contains the the value of smallest value.
	  */
	public DictEntry smallest() 
	{
		BinarySearchTreeNode r = root;
		if (r.getDictEntry()!=null)
			//iterates to the left of the try until it reaches a null value
			while (r.getLeft().getDictEntry()!=null) r=r.getLeft();
		return r.getDictEntry();
	}

	/**
	  * The method largest method returns the largest value in the BST.
	  *  
	  * @return					returns a DictEntry that contains the the value of largest value.
	  */
	public DictEntry largest() 
	{
		BinarySearchTreeNode r = root;
		if (r.getDictEntry()!=null)
			//iterates to the right of the try until it reaches a null value
			while (r.getRight().getDictEntry()!=null) r=r.getRight();
		return r.getDictEntry();
	}
}
