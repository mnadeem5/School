package cs2212.team4;

import java.io.Serializable;

/** 
 * This class provides the ability to create a Student and add/delete
 * a Student's grade among a few other things.
 *
 * @author Zaid Albirawi
 * @version 20 3/31/2014
 */


public class Student implements StudentADT, Serializable
{
	/* ************************************************************
	 * Instance Variables
	 ************************************************************ */

	// The Student Class version
	private static final long serialVersionUID = 1L;
	// The Student object first name, last name, and email.
	private String nameFirst = "", nameLast = "", number = "", email = "";
	// The Student object grades.
	private StudentGrades grades;

	/**
	 * Constructor that creates a student with a first & last name, id number, and email.
	 * 
	 * @param nameFirst the student's first name
	 * @param nameLast  the student's last name
	 * @param number    the student's number
	 * @param email     the student's email
	 */
	public Student(String nameFirst, String nameLast, String number,
			String email) {
		this.nameFirst = nameFirst;
		this.nameLast = nameLast;
		this.number = number;
		this.email = email;
		grades = new StudentGrades();
	}

	/* ************************************************************
	 * Accessor Methods
	 ************************************************************ */

	/**
	 * Gets the student's first name.
	 * 
	 * @return the student's first name
	 */
	public String getNameFirst() {
		return nameFirst;
	}

	/**
	 * Gets the student's last name.
	 * 
	 * @return the student's last name
	 */
	public String getNameLast() {
		return nameLast;
	}

	/**
	 * Gets the student's id number.
	 * 
	 * @return the student's id number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Gets the student's email.
	 * 
	 * @return the student's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets a grade from the student at the specified index.
	 * 
	 * @param grade the index of the grade to be returned
	 * @return      the grade for this student at the specified index
	 */
	public double getGrade(int grade) {
		return grades.getGrade(grade);
	}

	/**
	 * Gets the student's grade average.
	 * 
	 * @return an average based on all the student's grades
	 */
	public double getAvg() {
		return grades.getAvg();
	}

	/**
	 * Gets the student's assignment average.
	 * 
	 * @return an average based solely on this student's assignments
	 */
	public double getAsnAvg() {
		return grades.getAsnAvg();
	}

	/**
	 * Gets the student's exam average.
	 * 
	 * @return an average based solely on this student's exams
	 */
	public double getExmAvg() {
		return grades.getExmAvg();
	}

	/**
	 * Returns the number of grades associated with this student.
	 * 
	 * @return the number of grades associated with this student
	 */
	public int getNumGrades() {
		return grades.getGradeList().size();
	}

	/* ************************************************************
	 * Mutator Methods
	 ************************************************************ */

	/**
	 * Sets the student's first name.
	 * 
	 * @param nameFirst the first name this student will have
	 */
	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	/**
	 * Sets the student's last name.
	 * 
	 * @param nameLast the last name this student will have
	 */
	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}

	/**
	 * Sets the student's id number.
	 * 
	 * @param number the id number this student will have
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Sets the student's email.
	 * 
	 * @param email the email this student will have
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the student's average.
	 * 
	 * @param avg the new average this student will have
	 */
	public void setAvg(double avg) {
		grades.setAvg(avg);
	}

	/**
	 * Sets the student's assignment average.
	 * 
	 * @param asnAvg the new assignment average this student will have
	 */
	public void setAsnAvg(double asnAvg) {
		grades.setAsnAvg(asnAvg);
	}

	/**
	 * Sets the student's exam average.
	 * 
	 * @param exmAvg the new exam average this student will have
	 */
	public void setExmAvg(double exmAvg) {
		grades.setExmAvg(exmAvg);
	}

	/* ************************************************************
	 * Helper Methods
	 ************************************************************ */

	/**
	 * Add a grade for a student.
	 * 
	 * @param deliver the grade insertion position
	 * @param grade   the grade to be inserted
	 * @param type	  the deliverable type
	 * @param weight  the grade weight
	 * @return        true if the grade was inserted, false otherwise
	 */
	public boolean addGrade(int deliver, double grade, String type,
			double weight) {
		return grades.add(deliver, grade, type, weight);
	}

	/**
	 * Delete a grade for a student.
	 *
	 * @param deliver the index of the grade to be removed
	 * @param type	  the deliverable type for the grade to be removed
	 * @return        true if the grade was removed, false otherwise
	 */
	public boolean removeGrade(int deliver, String type) {
		return grades.remove(deliver, type);
	}

	/**
	 * Method to check equality between students.
	 * 
	 * @param stud	the student to be compared to	
	 * @return      true if the students are equal, false otherwise
	 */
	public boolean equals(Student stud) {
		if (this.toString().equalsIgnoreCase(stud.toString()))
			return true;
		return false;
	}

	/**
	 * Returns a string containing a student's attributes and grades.
	 * 
	 * @param delivers an array of grades for this student
	 * @return         all the student information in the form of a string
	 */
	public String toStringGrade(Object [] delivers) {
		String str;
		str = "\"" +  nameFirst+ "\", \"" +  nameLast+ "\", \"" + number + "\""
				+ ", \"" + email + "\"";

		for (int i = 0; i < delivers.length; i++) {
			str += ", \"" + grades.getGrade((int)delivers[i]) + "\"";				
		}
		return str + "\n";
	}

	/**
	 * Returns a string containing a student's attributes.
	 * 
	 * @return the student's attribute information in the form of a string
	 */
	public String toString() {
		return ("\"" + nameFirst + "\", \"" + nameLast + "\", \"" + number
				+ "\"" + ", \"" + email + "\"\n");
	}
}