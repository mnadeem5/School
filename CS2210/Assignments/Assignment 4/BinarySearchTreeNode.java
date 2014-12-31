/**
 * BinarySearchTreeNode is a node that will help construct a BinarySearchTree store the DictEntry data in its root.
 * 
 * @author Zaid Albirawi
 * Student #: 
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #4
 * 
 * @version 1.0 15/11/2013
 */
public class BinarySearchTreeNode
{
	 DictEntry root=null;
	 BinarySearchTreeNode left=null;
	 BinarySearchTreeNode right=null;
	
	public BinarySearchTreeNode (DictEntry root){this.root=root;}
	
	public DictEntry getDictEntry(){return root;}
	
	public BinarySearchTreeNode getLeft()
	{
		//Initializes the left node and prevents the program from fetching a null value.
		if (left==null) left = new BinarySearchTreeNode(null);
		return left;
	}
	
	public BinarySearchTreeNode getRight()
	{
		//Initializes the right node and prevents the program from fetching a null value.
		if (right==null) right = new BinarySearchTreeNode(null);
		return right;
	}
	
	public void setRoot (DictEntry root) {this.root=root;}
	
	public void setLeft(DictEntry left){this.left=new BinarySearchTreeNode (left);}
	
	public void setRight(DictEntry right){this.right=new BinarySearchTreeNode (right);}
	
	public void nullLeft(){left=null;}
	
	public void nullRight(){right=null;}
}
