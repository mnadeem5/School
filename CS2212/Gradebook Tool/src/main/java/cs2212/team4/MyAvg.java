package cs2212.team4;

import java.util.ArrayList;

/**
*
* A class that calculates what mark a student must receive to achieve a certain average
*
* team4-gradebook application
*
* @author Zaid Albirawi
* @version 1.6 3/25/2014
*/
public class MyAvg {
	Course course;
	Student student;
	
	/**
	 * Constructor that creates the myAvg object based off the student and course
	 * 
	 * @param student The student for whom we want to calculate needed grade
	 * @param course The course for which we will calculate that needed grade
	 * 
	 */
	public MyAvg(Course course, Student student) {
		this.course = course;
		this.student = student;
	}
	
	/**
	 * Method to calculate needed grade based on passed average
	 * 
	 * @param reqAvg required average for the course that the student wishes to achieve
	 * @return the grade needed with the remaining weight to achieve desired average
	 */
	public double calcAvg(double reqAvg) {
		ArrayList<Integer> delivers = new ArrayList<Integer>();
		double remWeight = 0;
		double currAvg = 0;
		for (int i = 0; i < course.getDeliverableListSize(); i++)
			if (course.getDeliverable(i) != null)
				delivers.add(i);

		for (int i = 0; i < delivers.size(); i++) {
			if (student.getGrade(delivers.get(i)) != -1) {
				currAvg += student.getGrade(delivers.get(i))
						* course.getDeliverable(delivers.get(i)).getWeight()
						/ 100;
				remWeight += course.getDeliverable(delivers.get(i)).getWeight() / 100;
			}
		}
		return (reqAvg - currAvg) / (1 - remWeight);
	}
}
