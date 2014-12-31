package cs2212.team4;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * The StudentGrades class will contain all the grades for each Student.
 *
 * team4-gradebook application
 *
 * @author Zaid Albirawi
 * @version 1.0 3/1/2014
 */

public class StudentGrades implements StudentGradesADT, Serializable
{
	/* ************************************************************
	* Instance Variables
	************************************************************ */
	
	// The StudentGrades Class vegetGradeListrsion
	private static final long serialVersionUID = 1L;
	// The StudentGrades object Grade objects list.
	private ArrayList<Grade> grades;
	// The StudentGrades object assignment grades list.
	private ArrayList<Integer> asn;
	// The StudentGrades object exam grades list.
	private ArrayList<Integer> exm;
	// The StudentGrades object other grades list.
	private ArrayList<Integer> other;
	// The StudentGrades object average, assignment average, and exam average.
	private double avg, asnAvg, exmAvg;

	private transient final String ASN = "assignment", EXM = "exam";
	
	private double asnWeight=0, exmWeight=0;
	
	/**
	  * Constructor that creates a List of a student's grades, and 2 sub-lists based on exams or assignments
	  */
	public StudentGrades() {
		grades = new ArrayList<Grade>();
		asn = new ArrayList<Integer>();
		exm = new ArrayList<Integer>();
		other = new ArrayList<Integer>();
        calcAvg();
        calcAsnAvg();
        calcExmAvg();
	}
	
	/* ************************************************************
	* Accessor Methods
	************************************************************ */
	
	/**
	  * Gets the student's average
	  * 
	  * @return the student's average
	  * 
	  */
	public double getAvg() {
		return avg;
	}
	
	/**
	  * Gets the student's assignment average
	  * 
	  * @return the student's assignment average
	  * 
	  */
	public double getAsnAvg() {
		return asnAvg;
	}

	/**
	  * Gets the student's exam average
	  * 
	  * @return the student's exam average
	  * 
	  */
	public double getExmAvg() {
		return exmAvg;
	}
	
	/**
	  * Gets the student's grade based on the index passed
	  * 
	  * @param grade The index of the grade in the student's list of grades
	  * @return The student's grade at this index. Return -1 if index is out of bounds of the grades list
	  * 
	  */
	public double getGrade(int grade) {
		if (grade >= grades.size() || grade<0)
			return -1;
		if (grades.get(grade)==null)
			return -1;
		return grades.get(grade).getGrade();
	}
	
	/**
	 * Gets a reference to the grades Arraylist of the student
	 * 
	 * @return A reference to the grades list of the student
	 */
	public ArrayList<Grade> getGradeList() {
		return grades;
	}
	/* ************************************************************
	* Mutator Methods
	************************************************************ */
	
	/**
	  * Sets the student's overall average
	  * 
	  * @param avg The new overall average for the student
	  * 
	  */
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	/**
	  * Sets the student's assignment average
	  * 
	  * @param asnAvg The new assignment average for the student
	  * 
	  */
	public void setAsnAvg(double asnAvg) {
		this.asnAvg = asnAvg;
		calcAvg();
	}
	
	/**
	  * Sets the student's exam average
	  * 
	  * @param exmAvg The new exam average for the student
	  * 
	  */
	public void setExmAvg(double exmAvg) {
		this.exmAvg = exmAvg;
		calcAvg();
	}
	
	/* ************************************************************
	* Helper Methods
	************************************************************ */
	
	/**
	  * Calculates the student's overall average
	  * 
	  * @return The calculated student's overall average. Returns -1 if the student has no grades availble
	  * 
	  */
	private void calcAvg() {
		double avg = 0, weight = 0, tempAvg=0, tempWeight=0;
		Grade temp;
		if (other==null||other.isEmpty()) {
			if (asnAvg>-1){
				tempAvg=asnAvg*asnWeight;
				tempWeight=asnWeight;
			}
			if (exmAvg>-1){
				tempAvg+=exmAvg*exmWeight;
				tempWeight+=exmWeight;
			}
			this.avg = tempAvg/tempWeight;
			return;
		}
		for (int i = 0; i < other.size(); i++) {
			temp = grades.get(other.get(i));
            if (temp==null)
                break;
			weight += temp.getWeight();
			avg += temp.getGrade() * temp.getWeight();
		}
		this.avg = (asnAvg*asnWeight+exmAvg*exmWeight+avg) / (weight+asnWeight+exmWeight);
	}
	
	/**
	  * Calculates the student's assignment average
	  * 
	  * @return The calculated student's assignment average. Returns -1 if the student has no assignment grades available
	  * 
	  */
	private void calcAsnAvg() {
		double avg = 0;
		double weight = 0;
		Grade temp;
		if (asn.isEmpty()) {
			asnAvg = -1;
			return;
		}
		for (int i = 0; i < asn.size(); i++) {
			temp = grades.get(asn.get(i));
            if (temp==null)
                break;
			weight += temp.getWeight();
			avg += temp.getGrade() * temp.getWeight();
		}
		asnAvg = avg / weight;
		asnWeight=weight;
	}
	
	/**
	  * Calculates the student's exam average
	  * 
	  * @return The calculated student's exam average. Returns -1 if the student has no exam grades available
	  * 
	  */
	private void calcExmAvg() {
		double avg = 0;
		Grade temp;
		double weight = 0;
		if (exm.isEmpty()) {
			exmAvg = -1;
			return;
		}
		for (int i = 0; i < exm.size(); i++) {
			temp = grades.get(exm.get(i));
            if (temp==null)
                break;
			weight += temp.getWeight();
			avg += temp.getGrade() * temp.getWeight();
		}
		exmAvg = avg / weight;
		exmWeight=weight;
	}
	
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
	public boolean add(int deliver, double grade, String type, double weight) {
		boolean boolExm = false, boolAsn = false;
		if (deliver >= grades.size())							// if deliverable number is greater than size of grades array
			for (int i = grades.size(); i < deliver + 1; i++)	// add a number of null grade objects to array until deliverable number is reached
				grades.add(null);
		if (type.equalsIgnoreCase(EXM)) {
			exm.add(deliver);
			boolExm = true;
		} else if (type.equalsIgnoreCase(ASN)) {
			asn.add(deliver);
			boolAsn = true;
		} else
			other.add(deliver);
		grades.set(deliver, (new Grade(grade, weight)));
		if (boolExm)
			calcExmAvg();
		else if (boolAsn)
			calcAsnAvg();
		calcAvg();
		return true;
	}

	/**
	  * Removes a grade from this student
	  * 
	  * @param deliver The grade removal index point
	  * @param type	The type of the deliverable
	  * @return true if the grade was removed successfully, false otherwise
	  * 
	  */
	public boolean remove(int deliver, String type) {
		boolean boolExm = false, boolAsn = false;
		if (deliver>=grades.size()||grades.get(deliver) == null)
			return false;
		if (type.equalsIgnoreCase(EXM))
			if (exm.contains(deliver)) {
				exm.remove(exm.indexOf(deliver));
				boolExm = true;
			} else
				return false;
		else if (type.equalsIgnoreCase(ASN))
			if (asn.contains(deliver)) {
				asn.remove(asn.indexOf(deliver));
				boolAsn = true;
			} else
				return false;
		else
			if (other.contains(deliver))
				other.remove(other.indexOf(deliver));
			else
				return false;

		grades.set(deliver, null);
        if (boolExm)
			calcExmAvg();
		else if (boolAsn)
			calcAsnAvg();
        calcAvg();

        return true;
	}
}
