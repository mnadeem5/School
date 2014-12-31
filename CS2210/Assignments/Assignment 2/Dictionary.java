/**
 * Dictionary is java class that implements DictionaryADT and uses a hashtable algorithm to store DictEntries.
 * 
 * @author Zaid Albirawi
 * Student #: 
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #2
 * 
 * @version 1.0 15/10/2013
 */

/**
 * Imports java.util.LinkedList that will help us implements the hashtable.
 */
import java.util.LinkedList;
public class Dictionary implements DictionaryADT
{
	private int size;
	private int n=0;
	private LinkedList<DictEntry> [] hashTable;
	
	/**
	  * The constructor of the class Dictionary that will take int size and creates a dictionary 
	  * 
	  * @param		size		an int that determines the size of the Dictionary.
	  * 
	  */
	public Dictionary(int size) throws DictionaryException 
	{
		LinkedList<DictEntry> [] hashTable=new LinkedList [size];
		this.size=size;
		this.hashTable=hashTable;
		/*for (int i=0; i<256; i++)
		{
			insert (new DictEntry((""+(char)i), i));
			//System.out.println(hashTable[i].element().getKey());
		}
		*/
	}
	
	/**
	  * insert is a method of the class Dictionary that inserts the DictEntries into the Dictionary.
	  * 
	  * @param		pair		a DictEntry that holds the key and code values of Dictionary entries.
	  * 
	  * @return					returns a 0 if there were no collisions and 1 if there was a collision.
	  * 
	  */
	public int insert (DictEntry pair) throws DictionaryException
	{	
		/**
		 * Checks if the key already exists in the Dictionary.
		 */
		if (find(pair.getKey())!=null)
		{
			throw new DictionaryException("This key has already been inserted into the Dictionary.");
		}
		/**
		 * Checks if n has exceeded the allowed number of entries.
		 */
		else
		{
			/**
			 * inserts the DictEntry to the Dictionary if that part of the array is still empty.
			 */
			if (hashTable[(pair.getCode()%size)]==null)
			{
				n++;
				hashTable[(pair.getCode()%size)]=new LinkedList ();
				hashTable[(pair.getCode()%size)].add(pair);
				//System.out.println("["+pair.getKey()+", "+pair.getCode()+"] has been added to the Dictionary at: ("+(pair.getCode()%size)+", 0)");
				return 0;
			}
			/**
			 * inserts the DictEntry to the Dictionary in the next available linkedlist spot.
			 */
			else
			{
				n++;
				//System.out.println("Collision at :"+(pair.getCode()%size));
				hashTable[(pair.getCode()%size)].add(pair);
				//System.out.println("["+pair.getKey()+", "+pair.getCode()+"] has been added to the Dictionary at: ("+(pair.getCode()%size)+", "+(hashTable[(pair.getCode()%size)].size()-1)+")");
				return 1;
			}
		}
	}

	/**
	  * remove is a method of the class Dictionary that removes DictEntries from the Dictionary.
	  * 
	  * @param		key		a String that contain the value of the key that needs to be removed.
	  * 
	  */
    public void remove (String key) throws DictionaryException
    {
    	if (find(key)==null)
		{
			throw new DictionaryException("");
		}
		else
		{
			/**
			 * Uses the helper method findToRemove to locate the position of the key.
			 */
			int [] location = new int [2];
			location = findToRemove(key);
			
			/**
			 * removes the entry.
			 */
			hashTable[location[0]].remove(location[1]);
			n--;
		}
    }

	/**
	  * find is a method of the class Dictionary that find the DictEntries in the Dictionary.
	  * 
	  * @param		pair		a DictEntry that holds the key and code values of Dictionary entries.
	  * 
	  * @return					returns a 0 if there were no collisions and 1 if there was a collision.
	  * 
	  */
    public DictEntry find (String key)
	{
    	/**
		 * Loops through the Dictionary.
		 */
    	for (int i=0; i<hashTable.length; i++)
    	{
    		/**
    		 * Prevents null pointer exceptions.
    		 */
    		if (hashTable[i]!=null)
    		{
    			/**
    			 * Loops through the LinkedList.
    			 */
    			for (int j=0; j< hashTable[i].size(); j++)
        		{
    				/**
    				 * Checks if the keys match.
    				 */
        			if (key.equals(hashTable[i].get(j).getKey()))
        			{
        				//System.out.println("This key, "+hashTable[i].get(j).getKey()+", with the code, "+hashTable[i].get(j).getCode()+", is located at hashTable["+i+"], and LinkedList position ["+j+"]");
        				return hashTable[i].get(j);
        			}
        		}
    		}
    	}
    	return null;
	}
    
    /**
	  * numElements is a method of the class Dictionary that returns the number of elements in the Dictionary.
	  * 
	  * @return					returns the number of the DictEntries in the Dictionary.
	  * 
	  */
    public int numElements(){return n;}
    
    /**
	  * findToRemove is a helper method of the class Dictionary that returns the location of the key that needs to be removed.
	  * 
	  * @return					returns an array of 2 integers that refers to the location of the key.
	  * 
	  */
    private int [] findToRemove (String key)
    {
    	int [] location = new int [2];
    	
    	/**
		 * Loops through the Dictionary.
		 */
    	for (int i=0; i<hashTable.length; i++)
    	{
    		/**
    		 * Prevents null pointer exceptions.
    		 */
    		if (hashTable[i]!=null)
    		{
    			/**
    			 * Loops through the LinkedList.
    			 */
    			for (int j=0; j< hashTable[i].size(); j++)
        		{
    				/**
    				 * Checks if the keys match.
    				 */
        			if (key.equals(hashTable[i].get(j).getKey()))
        			{
        				location [0]=i;
        				location [1]=j;
        				return location;
        			}
        		}
    		}
    	}
    	return null;
    }
}