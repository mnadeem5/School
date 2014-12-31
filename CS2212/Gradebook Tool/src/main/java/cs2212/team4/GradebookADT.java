package cs2212.team4;

/**
 *
 * GradebookADT implements the method interface for the Gradebook class.
 *
 * @author Zaid Albirawi
 * @version 2.0 3/31/2014
 */


public interface GradebookADT 
{
	/**
	 * Returns a course object from the gradebook.
	 * 
	 * @param crs the index of the course to be returned
	 * @return    the course at the specified index
	 */
	public Course getCourse(int crs);

	/**
	 * Returns an int signifying the size of the course list.
	 * 
	 * @return the size of the list of courses in the gradebook
	 */
	public int getCourseListSize();

	/**
	 * Gets the current value of the previous course in the list.
	 * 
	 * @return the previous course in the list
	 */
	public Course getPrevCourse();

	/**
	 * Sets the previous course property.
	 * 
	 * @param prevCourse the course to be set as the current previous course
	 */
	public void setPrevCourse(Course prevCourse);

	/**
	 * Stores the courses from the gradebook.
	 * 
	 * @return true if the courses were successfully exported, false otherwise
	 */
	public boolean store();

	/**
	 * Finds the index of a specified course in the gradebook.
	 * 
	 * @param crs  the course to be searched for
	 * @return     the index of the course location in the gradebook. If course not found, return -1 
	 */
	public int findCourse(Course crs);

	/**
	 * Adds a course to the gradebook.
	 * 
	 * @param name	the name of the course to be added
	 * @param term the term of the course to be added
	 * @param code the code of the course to be added
	 * @return     true if the course was added, false if course already exists
	 */
	public boolean addCourse(String name, String term, String code);

	/**
	 * Removes a specified course from the gradebook.
	 * 
	 * @param crs the course to be deleted
	 * @return    true if the course was deleted, false if course does not exist
	 */
	public boolean removeCourse(Course crs);
}
