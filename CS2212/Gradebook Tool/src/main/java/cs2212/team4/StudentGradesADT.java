package cs2212.team4;

import java.util.ArrayList;

/**
 *
 * StudentGradesADT implements the method interface for the StudentGrades class.
 *
 * @author Zaid Albirawi
 * @version 1.0 3/1/2014
 */

public interface StudentGradesADT
{	
	/**
	  * Gets the student's average
	  * 
	  * @return the student's average
	  * 
	  */
	public double getAvg();
	
	/**
	  * Gets the student's assignment average
	  * 
	  * @return the student's assignment average
	  * 
	  */
	public double getAsnAvg();

	/**
	  * Gets the student's exam average
	  * 
	  * @return the student's exam average
	  * 
	  */
	public double getExmAvg();
	
	/**
	  * Gets the student's grade based on the index passed
	  * 
	  * @param grade The index of the grade in the student's list of grades
	  * @return The student's grade at this index. Return -1 if index is out of bounds of the grades list
	  * 
	  */
	public double getGrade(int grade);
	
	/**
	 * Gets a reference to the grades Arraylist of the student
	 * 
	 * @return A reference to the grades list of the student
	 */
	public ArrayList<Grade> getGradeList();
	
	/**
	  * Sets the student's overall average
	  * 
	  * @param avg The new overall average for the student
	  * 
	  */
	public void setAvg(double avg);
	
	/**
	  * Sets the student's assignment average
	  * 
	  * @param asnAvg The new assignment average for the student
	  * 
	  */
	public void setAsnAvg(double asnAvg);
	
	/**
	  * Sets the student's exam average
	  * 
	  * @param exmAvg The new exam average for the student
	  * 
	  */
	public void setExmAvg(double exmAvg);
	
	/**
	  * Adds a grade for the student
	  * 
	  * @param deliver The grade insertion position
	  * @param grade The grade to be inserted
	  * @param type	The deliverable type
	  * @param weight The grade weight
	  * @return true if the grade was inserted successfully, false otherwise
	  * 
	  */
	public boolean add(int deliver, double grade, String type, double weight);

	/**
	  * Removes a grade from this student
	  * 
	  * @param deliver The grade removal index point
	  * @param type	The type of the deliverable
	  * @return true if the grade was removed successfully, false otherwise
	  * 
	  */
	public boolean remove(int deliver, String type);
}
