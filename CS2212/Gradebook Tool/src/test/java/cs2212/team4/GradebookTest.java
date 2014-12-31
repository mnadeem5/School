package cs2212.team4;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Steve Juarez
 */

public class GradebookTest 
{	
	Gradebook gradebook;
	Course course;

	@Before
	public void testGradebook()
	{
		gradebook = new Gradebook();
		gradebook.addCourse("Writing", "A", "2020");
		course = new Course("Writing", "A", "2020");
	}

	@Test
	public void testGetCourse()
	{
		assertTrue(course.equals(gradebook.getCourse(gradebook.findCourse(course))));
		assertEquals(null, gradebook.getCourse(-1));
	}

	@Test
	public void testGetCourseListSize()
	{
		int numCourses = 0;
		for(int i=0;i<gradebook.getCourseListSize();i++){if(gradebook.getCourse(i)!=null)numCourses++;}
		assertEquals(numCourses, gradebook.getCourseListSize());
	}

	@Test
	public void testGetPrevCourse()
	{
		gradebook.setPrevCourse(course);
		assertTrue(gradebook.getPrevCourse().equals(course));
	}

	@Test
	public void testSetPrevCourse()
	{
		gradebook.setPrevCourse(new Course("Title", "Term", "Code"));
		assertTrue(gradebook.getPrevCourse().equals(new Course("Title", "Term", "Code")));
	}

	@Test
	public void testFindCourse()
	{
		assertEquals(-1, gradebook.findCourse(new Course("Title", "Term", "Code")));
		assertTrue(gradebook.findCourse(course) >= 0);
	}

	@Test
	public void testAddCourse()
	{
		assertTrue(gradebook.addCourse("Title", "Term", "Code"));		
		assertFalse(gradebook.addCourse("Writing", "A", "2020"));
	}

	@Test
	public void testRemoveCourse()
	{
		assertTrue(gradebook.removeCourse(course));
		assertFalse(gradebook.removeCourse(new Course ("Title", "Term", "Code")));
	}
}