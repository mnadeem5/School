package cs2212.team4;

/**
 *
 * StudentADT implements the method interface for the Student class.
 *
 * @author Zaid Albirawi
 * @version 2.0 3/31/2014
 */


public interface StudentADT
{	
	/**
	 * Gets the student's first name.
	 * 
	 * @return the student's first name
	 */
	public String getNameFirst();

	/**
	 * Gets the student's last name.
	 * 
	 * @return the student's last name
	 */
	public String getNameLast();

	/**
	 * Gets the student's id number.
	 * 
	 * @return the student's id number
	 */
	public String getNumber();

	/**
	 * Gets the student's email.
	 * 
	 * @return the student's email
	 */
	public String getEmail();

	/**
	 * Gets a specific grade of the student at the index passed
	 * 
	 * @param grade The grade at the index that we want returned
	 * @return the grade for this student at the index we passed
	 * 
	 */
	public double getGrade(int grade);

	/**
	 * Gets the student's average
	 *
	 * @return      the grade for this student at the specified index
	 */
	public double getAvg();

	/**
	 * Gets the student's assignment average.
	 * 
	 * @return an average based on all the student's grades
	 */
	public double getAsnAvg();

	/**
	 * Gets the student's exam average.
	 * 
	 * @return an average based solely on this student's exams
	 */
	public double getExmAvg();

	/**
	 * Returns the number of grades associated with this student.
	 * 
	 * @return the number of grades associated with this student
	 */
	public int getNumGrades();

	/**
	 * Sets the student's first name.
	 * 
	 * @param nameFirst the first name this student will have
	 */
	public void setNameFirst(String nameFirst);

	/**
	 * Sets the student's last name.
	 * 
	 * @param nameLast the last name this student will have
	 */
	public void setNameLast(String nameLast);

	/**
	 * Sets the student's id number.
	 * 
	 * @param number the id number this student will have
	 */
	public void setNumber(String number);

	/**
	 * Sets the student's email.
	 * 
	 * @param email the email this student will have
	 */
	public void setEmail(String email);

	/**
	 * Sets the student's average.
	 * 
	 * @param avg the new average this student will have
	 */
	public void setAvg(double avg);

	/**
	 * Sets the student's assignment average.
	 * 
	 * @param asnAvg the new assignment average this student will have
	 */
	public void setAsnAvg(double asnAvg);

	/**
	 * Sets the student's exam average.
	 * 
	 * @param exmAvg the new exam average this student will have
	 */
	public void setExmAvg(double exmAvg);

	/**
	 * Add a grade for a student.
	 * 
	 * @param deliver the grade insertion position
	 * @param grade   the grade to be inserted
	 * @param type	  the deliverable type
	 * @param weight  the grade weight
	 * @return        true if the grade was inserted, false otherwise
	 */
	public boolean addGrade(int deliver, double grade, String type, double weight);

	/**
	 * Delete a grade for a student.
	 *
	 * @param deliver the index of the grade to be removed
	 * @param type	  the deliverable type for the grade to be removed
	 * @return        true if the grade was removed, false otherwise
	 */
	public boolean removeGrade(int deliver, String type);

	/**
	 * Method to check equality between students.
	 * 
	 * @param stud	the student to be compared to	
	 * @return      true if the students are equal, false otherwise
	 */
	public boolean equals(Student stud);

	/**
	 * Returns a string containing a student's attributes and grades.
	 * 
	 * @param delivers an array of grades for this student
	 * @return         all the student information in the form of a string
	 */
	public String toStringGrade(Object [] delivers);

	/**
	 * Returns a string containing a student's attributes.
	 * 
	 * @return the student's attribute information in the form of a string
	 */
	public String toString();
}
