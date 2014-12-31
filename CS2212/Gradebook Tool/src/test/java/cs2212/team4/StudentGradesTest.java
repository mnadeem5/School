package cs2212.team4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class StudentGradesTest {

	StudentGrades stug1, stug2;
	ArrayList<Grade> grades;

	@Before
	public void testStudentGrades() {
		stug1 = new StudentGrades();
		stug1.add(0, 85, "assignment", 20);
		stug1.add(1, 76, "assignment", 20);
		stug1.add(3, 55, "exam", 30);

		stug2 = new StudentGrades();
		stug2.add(0, 55, "assignment", 10);
		stug2.add(1, 68, "assignment", 10);
		stug2.add(2, 70, "assignment", 20);

	}

	@Test
	public void testGetAvg() {
		Assert.assertTrue(69.57142857142857==stug1.getAvg());
	}

	@Test
	public void testGetAsnAvg() {
		Assert.assertTrue(80.5==stug1.getAsnAvg());
	}

	@Test
	public void testGetExmAvg() {
		Assert.assertTrue(55.0==stug1.getExmAvg());
	}

	@Test
	public void testGetGrade() {
		// if grade is greater or equal to grades size, return -1
		Assert.assertTrue(-1.0==stug1.getGrade(4));
		// if grade is null, return -1
		Assert.assertTrue(-1.0==stug1.getGrade(2));
		// otherwise get grade
		Assert.assertTrue(85.0==stug1.getGrade(0));
		Assert.assertTrue(76.0==stug1.getGrade(1));
		Assert.assertTrue(55.0==stug1.getGrade(3));
	}

	@Test
	public void testGetGradeList() {
		// Assert.assertEquals( grades, stug2.getGradeList() );

	}

	@Test
	public void testSetAvg() {
		stug1.setAvg(88);
		Assert.assertTrue(88.0==stug1.getAvg());
	}

	@Test
	public void testSetAsnAvg() {
		stug1.setAsnAvg(78);
		Assert.assertTrue(78.0==stug1.getAsnAvg());
	}

	@Test
	public void testSetExmAvg() {
		stug1.setExmAvg(64);
		Assert.assertTrue(64.0==stug1.getExmAvg());
	}

	@Test
	public void testAdd() {
		// add a student grade where the deliverable number is greater than the
		// size of grades array
		stug2.add(5, 80, "exam", 25);
		Assert.assertEquals(6, stug2.getGradeList().size());
		// add a student grade where type is "exam"

		// add a student grade where type is "assignment"
		Assert.assertTrue(stug2.add(6, 70, "assignment", 20));

		// add a student grade where type is neither
		Assert.assertTrue(stug2.add(7, 80, "bonus", 5));

	}

	@Test
	public void testRemove() {
		stug2.add(3, 94, "bonus", 5);
		stug2.add(4, 66, "exam", 20);
		stug2.add(5, 86, "assignment", 20);
		stug2.add(6, 50, "exam", 20);

		// remove a grade that has a deliverable number greater than gradelist
		// size, return false
		Assert.assertFalse(stug2.remove(7, "exam"));

		// remove a grade of an exam type, return true
		Assert.assertTrue(stug2.remove(6, "exam"));

		// remove a grade of an exam type, that has an incorrect deliverable
		// number, return false
		Assert.assertFalse(stug2.remove(5, "exam"));

		// remove a grade of an assignment type, return true
		Assert.assertTrue(stug2.remove(5, "assignment"));

		// remove a grade of an assignment type, that has an incorrect
		// deliverable number, return false
		Assert.assertFalse(stug2.remove(4, "assignment"));

		// remove a grade of an "other" type that has a non-existing deliverable
		// number, return false
		Assert.assertFalse(stug2.remove(2, "bonus"));

		// remove a grade of an "other" type, return true
		Assert.assertTrue(stug2.remove(3, "bonus"));

		stug2.add(3, 94, "bonus", 5);
		stug2.add(4, 66, "exam", 20);
		stug2.remove(3, "bonus");
		Assert.assertFalse(stug2.remove(3, "bonus"));
	}

}
