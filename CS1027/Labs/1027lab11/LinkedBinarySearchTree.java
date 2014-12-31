/**
 * LinkedBinarySearchTree implements the BinarySearchTreeADT interface 
 * with links.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

public class LinkedBinarySearchTree<T>  extends LinkedBinaryTree<T>
                                  implements BinarySearchTreeADT<T>
{
   /**
    * Creates an empty binary search tree.
    */
   public LinkedBinarySearchTree() 
   {
      super();
   }
   
   /**
    * Creates a binary search with the specified element as its root.
    *
    * @param element  the element that will be the root of the new binary
    *                 search tree
    */
   public LinkedBinarySearchTree (T element) 
   {
      super (element);
   }

   /**
    * Adds the specified object to the binary search tree in the
    * appropriate position according to its key value.  Note that
    * equal elements are added to the right.
    *
    * @param element  the element to be added to the binary search tree
    */
   public void addElement (T element) 
   {
      BinaryTreeNode<T> temp = new BinaryTreeNode<T> (element);
      Comparable<T> comparableElement = (Comparable<T>)element;

      if (isEmpty())
         root = temp;
      else 
      {
         BinaryTreeNode<T> current = root;
         boolean added = false;

         while (!added) 
         {
            if (comparableElement.compareTo(current.getElement()) < 0)
            {
               if (current.getLeft() == null) 
               {
                  current.setLeft(temp);
                  added = true;
               } 
               else
                  current = current.getLeft();
            }
            else
            {
               if (current.getRight() == null) 
               {
                  current.setRight(temp);
                  added = true;
               } 
               else
                  current = current.getRight();
            }
         }
      }
      
      count++;
   }
   
   /**
    * Removes the first element that matches the specified target
    * element from the binary search tree and returns a reference to
    * it.  Throws a ElementNotFoundException if the specified target
    * element is not found in the binary search tree.
    *
    * @param targetElement              the element being sought in the binary 
    *                                   search tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T removeElement (T targetElement)
                          throws ElementNotFoundException 
   {
      T result = null;

      if (!isEmpty())
      {
         if (((Comparable)targetElement).equals(root.getElement())) 
         {
            result =  root.getElement();
            root = replacement (root);
            count--;
         }
         else 
         {
            BinaryTreeNode<T> current, parent = root;
            boolean found = false;

            if (((Comparable)targetElement).compareTo(root.getElement()) < 0)
               current = root.getLeft();
            else
               current = root.getRight();

            while (current != null && !found) 
            {
               if (targetElement.equals(current.getElement())) 
               {
                  found = true;
                  count--;
                  result =  current.getElement();
          
                  if (current == parent.getLeft())
                  {
                     parent.setLeft(replacement (current));
                  }
                  else
                  {
                     parent.setRight(replacement (current));
                  }
               }
               else 
               {
                  parent = current;
         
                  if (((Comparable)targetElement).compareTo(current.getElement()) < 0)
                     current = current.getLeft();
                  else
                     current = current.getRight();
               }
            } //while
            
            if (!found)
               throw new ElementNotFoundException("binary search tree");
         }
      } // end outer if

      return result;
   }

   /**
    * Removes elements that match the specified target element from 
    * the binary search tree. Throws a ElementNotFoundException if 
    * the sepcified target element is not found in this tree.
    *
    * @param targetElement              the element being sought in the binary \
    *                                   search tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public void removeAllOccurrences (T targetElement)
                                    throws ElementNotFoundException 
   {
      removeElement(targetElement);
      
      try
      {
         while (contains( (T) targetElement))
            removeElement(targetElement);
      }
      
      catch (Exception ElementNotFoundException)
      {
      }
   }

   /**
    * Removes the node with the least value from the binary search
    * tree and returns a reference to its element.  Throws an
    * EmptyBinarySearchTreeException if this tree is empty. 
    *
    * @return                           a reference to the node with the least 
    *                                   value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T removeMin() throws EmptyCollectionException 
   {
      T result = null;

      if (isEmpty())
         throw new EmptyCollectionException ("binary search tree");
      else 
      {
         if (root.getLeft() == null) 
         {
            result = root.getElement();
            root = root.getRight();
         }
         else 
         {
            BinaryTreeNode<T> parent = root;
            BinaryTreeNode<T> current = root.getLeft();
            while (current.getLeft() != null) 
            {
               parent = current;
               current = current.getLeft();
            }
            result =  current.getElement();
            parent.setLeft(current.getRight());
         }

         count--;
      }
 
      return result;
   }

   /**
    * Removes the node with the highest value from the binary
    * search tree and returns a reference to its element.  Throws an
    * EmptyBinarySearchTreeException if this tree is empty. 
    *
    * @return  a reference to the node with the highest value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T removeMax() throws EmptyCollectionException 
   {
	   T result = null;

	   if (isEmpty())
	        throw new EmptyCollectionException ("binary tree");
	   else 
	   {
	      if (root.getRight() == null) 
	      {
	    	  result =  root.getElement();
	          root = root.getLeft();
	      }
	      else 
	      {
	    	  BinaryTreeNode<T> parent = root;
	          BinaryTreeNode<T> current = root.getRight();

	          while (current.getRight() != null) 
	          {
	             parent = current;
	             current = current.getRight();
	          }

	          result =  current.getElement();
	          parent.setRight(current.getLeft());
	      }
	      		count--;
	   }
	   return result;
   }


   /**
    * Returns the element with the least value in the binary search
    * tree. It does not remove the node from the binary search tree. 
    * Throws an EmptyBinarySearchTreeException if this tree is empty.
    *
    * @return  the element with the least value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T findMin() throws EmptyCollectionException 
   {
	   T result = null;

	   if (isEmpty())
           throw new EmptyCollectionException ("binary tree");
	   else 
	   {
		   BinaryTreeNode<T> current = root;
        
		   while (current.getLeft() != null)
			   current = current.getLeft();
       
		   result = current.getElement();
	   }
	   return result;
   }

   /**
    * Returns the element with the highest value in the binary
    * search tree.  It does not remove the node from the binary
    * search tree.  Throws an EmptyBinarySearchTreeException if this 
    * tree is empty.
    *
    * @return  the element with the highest value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T findMax() throws EmptyCollectionException 
   {
	   T result = null;

	   if (isEmpty())
           	throw new EmptyCollectionException ("binary tree");
	   else 
	   {
		   BinaryTreeNode<T> current = root;
      
		   while (current.getRight() != null)
			   current = current.getRight();

		   result = current.getElement();
	   }
	   return result; 
   }

   /**
    * Returns a reference to the specified target element if it is
    * found in the binary tree.  Throws a NoSuchElementException if
    * the specified target element is not found in this tree.
    *
    * @param targetElement  the element being sough in the binary tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T find (T targetElement) throws ElementNotFoundException 
   {
	   BinaryTreeNode<T> current = root; 
	   BinaryTreeNode<T> temp = current;
	   
	   if (!(current.getElement().equals(targetElement)) && (current.getLeft() !=null)&&(((Comparable)current.getElement()).compareTo(targetElement) > 0))
		   current = findAgain( targetElement, current.getLeft());

	   else if (!(current.getElement().equals(targetElement)) && (current.getRight() != null)) 
		   current = findAgain( targetElement, current.getRight()); 

	   if (!(current.getElement().equals(targetElement)))
		   throw new ElementNotFoundException ("binarytree");

	   return current.getElement();
   }

   /**
    * Returns a reference to the specified target element if it is
    * found in this tree.  
    *
    * @param targetElement  the element being sought in the tree
    * @param next           the tree node to being searching on
    */
   private BinaryTreeNode<T> findAgain (T targetElement, BinaryTreeNode<T> next) 
   {
	   BinaryTreeNode<T> current = next;
	   if (!(next.getElement().equals(targetElement)) && (next.getLeft() !=null) &&(((Comparable)next.getElement()).compareTo(targetElement) > 0))
		   next = findAgain( targetElement, next.getLeft()); 
	   else if (!(next.getElement().equals(targetElement)) && (next.getRight() != null))
		   next = findAgain( targetElement, next.getRight());                     
	      
	   return next;
   }

   /**
    * Returns a reference to a node that will replace the one
    * specified for removal.  In the case where the removed node has 

    * two children, the inorder successor is used as its replacement.
    *
    * @param node  the node to be removeed
    * @return      a reference to the replacing node
    */
   protected BinaryTreeNode<T> replacement (BinaryTreeNode<T> node) 
   {
      BinaryTreeNode<T> result = null;

      if ((node.getLeft() == null)&&(node.getRight()==null))
         result = null;
      
      else if ((node.getLeft() != null)&&(node.getRight()==null))
         result = node.getLeft();
      
      else if ((node.getLeft() == null)&&(node.getRight() != null))
         result = node.getRight();
      
      else
      {
         BinaryTreeNode<T> current = node.getRight();
         BinaryTreeNode<T> parent = node;
         
         while (current.getLeft() != null)
         {
            parent = current;
            current = current.getLeft();
         }
         
         if (node.getRight() == current)
            current.setLeft(node.getLeft());
         
         else
         {
            parent.setLeft(current.getRight());
            current.setRight(node.getRight());
            current.setLeft(node.getLeft());
         }
         
         result = current;
      }
      
      return result;
   }
}

