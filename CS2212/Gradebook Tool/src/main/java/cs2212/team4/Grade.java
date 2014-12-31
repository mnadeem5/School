package cs2212.team4;

import java.io.Serializable;

/**
 *
 * Grade is the class that will be used to store student grades.
 *
 * team4-gradebook application
 *
 * @author Zaid Albirawi
 * @version 1.0 3/1/2014
 */

public class Grade implements GradeADT, Serializable
{
	/* ************************************************************
	* Instance Variables
	************************************************************ */
	
	// The Grade Class version
	private static final long serialVersionUID = 1L;
	// The Grade object grade and weight.
	private double grade, weight;
	
	/**
	  * Constructor that creates a grade with a given number value and weight
	  * 
	  * @param grade The number value of the grade
	  * @param weight The weight value that this grade represents
	  * 
	  */
	public Grade(double grade, double weight) {
		this.grade = grade;
		this.weight = weight;
	}
	
	/* ************************************************************
	* Accessor Methods
	************************************************************ */
	
	/**
	  * Gets the grade value
	  * 
	  * @return The quantitative grade value
	  * 
	  */
	public double getGrade() {
		return grade;
	}
	
	/**
	  * Gets the weight that the grade represents
	  * 
	  * @return The weight of this grade
	  * 
	  */
	public double getWeight() {
		return weight;
	}
	
	/* ************************************************************
	* Mutator Methods
	************************************************************ */
	
	/**
	  * Set the grade value
	  * 
	  * @param grade The value that we want to set the grade to
	  * 
	  */
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	/**
	  * Sets grade weight
	  * 
	  * @param weight The value that we want to set the weight to
	  * 
	  */
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
