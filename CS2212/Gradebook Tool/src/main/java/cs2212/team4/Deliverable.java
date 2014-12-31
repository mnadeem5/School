package cs2212.team4;

import java.io.Serializable;

/**
 *
 * Deliverable is the class that will be used to store all course deliverables information.
 *
 * team4-gradebook application
 *
 * @author Zaid Albirawi
 * @version 2.0 3/1/2014
 */

public class Deliverable implements DeliverableADT, Serializable
{
	/* ************************************************************
	* Instance Variables
	************************************************************ */
	
	// The Deliverable Class version
	private static final long serialVersionUID = 1L;
	// The Deliverable name and type
	private String name = "", type = "";
	// The Deliverable weight
	private double weight;
	// The Deliverable id
	private int objId;
	
	/**
	  * Constructor that creates a deliverable with a given name, type, weight, and ID
	  * 
	  * @param name The name of the deliverable
	  * @param type The type of the deliverable
	  * @param weight The weight of the deliverable
	  * @param objId The deliverable ID
	  * 
	  */
	public Deliverable(String name, String type, double weight, int objId) {
		this.name = name;
		this.type = type;
		this.weight = weight;
		this.objId = objId;
	}
	
	/* ************************************************************
	* Accessor Methods
	************************************************************ */

	/**
	  * Gets the name of the deliverable
	  * 
	  * @return The deliverable's name
	  * 
	  */
	public String getName() {
		return name;
	}

	/**
	  * Gets the type of the deliverable
	  * 
	  * @return The deliverable type
	  * 
	  */
	public String getType() {
		return type;
	}

	/**
	  * Gets the weight of the deliverable
	  * 
	  * @return The deliverable's weight
	  * 
	  */
	public double getWeight() {
		return weight;
	}
	
	/**
	  * Gets ID of the deliverable
	  * 
	  * @return The derliverable's ID
	  * 
	  */
	public int getObjId() {
		return objId;
	}
	
	/* ************************************************************
	* Mutator Methods
	************************************************************ */
	
	/**
	  * Sets name of the deliverable
	  * 
	  * @param name The deliverable's requested name
	  * 
	  */
	public void setName(String name) {
		this.name = name;
	}

	/**
	  * Sets type of the deliverable
	  * 
	  * @param type	The deliverable's requested type
	  * 
	  */
	public void setType(String type) {
		this.type = type;
	}

	/**
	  * Sets weight of the deliverable
	  * 
	  * @param weight The deliverable's requested weight
	  * 
	  */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/* ************************************************************
	* Helper Methods
	************************************************************ */
	
	/**
	  * A method to test for equality between deliverables
	  * 
	  * @param deliver deliverable we want to compare to this one
	  * @return true if the deliverables are the same, false otherwise
	  * 
	  */
	public boolean equals(Deliverable deliver) {
		if (this.toString().equalsIgnoreCase(deliver.toString()))
			return true;
		return false;
	}
	
	/**
	  * A toString method
	  * 
	  * @return the deliverable information. Including name, type, and weight
	  * 
	  */
	public String toString() {
		return ("\"" + name + "\", \"" + type + "\", \"" + weight + "\"\n");
	}
}
