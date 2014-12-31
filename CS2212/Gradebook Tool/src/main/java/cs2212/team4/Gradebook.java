package cs2212.team4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This class contains functionality to manage a list of courses in a Gradebook.
 * 
 * @author Zaid Albirawi
 * @version 2.0 3/31/2014
 */

public class Gradebook implements GradebookADT, Serializable
{
	/* ************************************************************
	 * Instance Variables
	 ************************************************************ */

	// The Gradebook Class version
	private static final long serialVersionUID = 1L;
	// The Gradebook object Course object list.
	private ArrayList<Course> courseList;
	// The path to where the data will be saved.

	private Course prevCourse=null;
	
	private Properties properties=null;

	/**
	 * Constructor that creates an arrayList of courses representing a gradebook.
	 */
	public Gradebook() {
		if (!load())
			courseList = new ArrayList<Course>();
	}

	/* ************************************************************
	 * Accessor Methods
	 ************************************************************ */

	/**
	 * Returns a course object from the gradebook.
	 * 
	 * @param crs the index of the course to be returned
	 * @return    the course at the specified index
	 */
	public Course getCourse(int crs) {
		if (courseList.size() > crs && crs >= 0)
			return courseList.get(crs);
		return null;
	}

	/**
	 * Returns an int signifying the size of the course list.
	 * 
	 * @return the size of the list of courses in the gradebook
	 */
	public int getCourseListSize() {
		return courseList.size();
	}

	/**
	 * Gets the current value of the previous course in the list.
	 * 
	 * @return the previous course in the list
	 */
	public Properties getProperties() {
		return properties;
	}
	
	/**
	 * Gets the current value of the previous course in the list.
	 * 
	 * @return the previous course in the list
	 */
	public Course getPrevCourse() {
		return prevCourse;
	}

	/* ************************************************************
	 * Mutator Methods
	 ************************************************************ */

	/**
	 * Sets the previous course property.
	 * 
	 * @param prevCourse the course to be set as the current previous course
	 */
	public void setPrevCourse(Course prevCourse) {
		this.prevCourse = prevCourse;
	}

	/**
	 * Sets the properties course property.
	 * 
	 * @param properties the properties to be set as the email
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/* ************************************************************
	 * Helper Methods
	 ************************************************************ */

	/**
	 * Stores the courses from the gradebook.
	 * 
	 * @return true if the courses were successfully exported, false otherwise
	 */
	public boolean store() {
		try {
			File file = new File("gradebook-files/data.dat");
			file.mkdirs();
			if (file.exists())
				file.delete();
			ObjectOutputStream OUS = new ObjectOutputStream(
					new FileOutputStream(file));
			OUS.writeObject((Course) prevCourse);
			OUS.writeObject((Properties) properties);
			OUS.writeObject((ArrayList<Course>) courseList);
			OUS.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Loads the courses into the gradebook.
	 * 
	 * @return true if the courses were successfully imported, false otherwise
	 */
	@SuppressWarnings("unchecked")
	private boolean load() {
		try {
			ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(
					"gradebook-files/data.dat"));
			prevCourse = (Course)OIS.readObject();
			properties = (Properties)OIS.readObject();
			courseList = (ArrayList<Course>) OIS.readObject();
			OIS.close();
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Finds the index of a specified course in the gradebook.
	 * 
	 * @param crs  the course to be searched for
	 * @return     the index of the course location in the gradebook. If course not found, return -1 
	 */
	public int findCourse(Course crs) {
		for (int i = 0; i < courseList.size(); i++)
			if (courseList.get(i).equals(crs))
				return i;
		return -1;
	}

	/**
	 * Adds a course to the gradebook.
	 * 
	 * @param name	the name of the course to be added
	 * @param term the term of the course to be added
	 * @param code the code of the course to be added
	 * @return     true if the course was added, false if course already exists
	 */
	public boolean addCourse(String name, String term, String code) {
		Course crs;
		if (findCourse(crs = new Course(name, term, code)) != -1)
			return false;
		else
			courseList.add(crs);
		return true;
	}

	/**
	 * Removes a specified course from the gradebook.
	 * 
	 * @param crs the course to be deleted
	 * @return    true if the course was deleted, false if course does not exist
	 */
	public boolean removeCourse(Course crs) {
		int i;
		if ((i = findCourse(crs)) == -1)
			return false;
		else
			courseList.remove(i);
		return true;
	}
}
