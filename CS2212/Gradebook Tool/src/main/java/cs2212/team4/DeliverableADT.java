package cs2212.team4;

/**
 *
 * DeliverableADT implements the method interface for the Deliverable class.
 *
 * @author Zaid Albirawi
 * @version 2.0 3/1/2014
 */


public interface DeliverableADT
{
	/**
	  * Gets the name of the deliverable
	  * 
	  * @return The deliverable's name
	  * 
	  */
	public String getName();

	/**
	  * Gets the type of the deliverable
	  * 
	  * @return The deliverable type
	  * 
	  */
	public String getType();

	/**
	  * Gets the weight of the deliverable
	  * 
	  * @return The deliverable's weight
	  * 
	  */
	public double getWeight();
	
	/**
	  * Gets ID of the deliverable
	  * 
	  * @return The derliverable's ID
	  * 
	  */
	public int getObjId();
	
	/**
	  * Sets name of the deliverable
	  * 
	  * @param name The deliverable's requested name
	  * 
	  */
	public void setName(String name);

	/**
	  * Sets type of the deliverable
	  * 
	  * @param type	The deliverable's requested type
	  * 
	  */
	public void setType(String type);

	/**
	  * Sets weight of the deliverable
	  * 
	  * @param weight The deliverable's requested weight
	  * 
	  */
	public void setWeight(double weight);
	
	/**
	  * A method to test for equality between deliverables
	  * 
	  * @param deliver deliverable we want to compare to this one
	  * @return true if the deliverables are the same, false otherwise
	  * 
	  */
	public boolean equals(Deliverable deliver);
	
	/**
	  * A toString method
	  * 
	  * return the deliverable information. Including name, type, and weight
	  * 
	  */
	public String toString();
}
