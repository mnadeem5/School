package cs2212.team4;

/**
 *
 * GradeADT implements the method interface for the Grade class.
 *
 * @author Zaid Albirawi
 * @version 1.0 3/1/2014
 */

public interface GradeADT
{	
	/**
	  * Gets the grade value
	  * 
	  * @return The quantitative grade value
	  * 
	  */
	public double getGrade();
	
	/**
	  * Gets the weight that the grade represents
	  * 
	  * @return The weight of this grade
	  * 
	  */
	public double getWeight();
	
	/**
	  * Set the grade value
	  * 
	  * @param grade The value that we want to set the grade to
	  * 
	  */
	public void setGrade(double grade);
	
	/**
	  * Sets grade weight
	  * 
	  * @param weight The value that we want to set the weight to
	  * 
	  */
	public void setWeight(double weight);
}
