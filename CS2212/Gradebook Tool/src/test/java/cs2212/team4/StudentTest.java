package cs2212.team4;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Steve Juarez
 */

public class StudentTest {

	Student student;

	@Before
	public void testStudent() {

		student = new Student("Joe", "Johns",  "123456789", "jj@uwo.ca");
		student.addGrade(0, 80.0, "Exam", 0.5);
		student.addGrade(1, 90.0, "Assignment", 0.5);
	}

	@Test
	public void testGetNameFirst() {
		assertTrue(student.getNameFirst().equals("Joe"));
	}

	@Test
	public void testGetNameLast() {
		assertTrue(student.getNameLast().equals("Johns"));
	}

	@Test
	public void testGetNumber() {
		assertTrue(student.getNumber().equals("123456789"));
	}

	@Test
	public void testGetEmail() {
		assertTrue(student.getEmail().equals("jj@uwo.ca"));
	}

	@Test
	public void testGetGrade() {
		assertTrue(student.getGrade(0) == 80.0);
	}

	@Test
	public void testGetAvg() {
		assertTrue(student.getAvg() == 85.0);
	}

	@Test
	public void testGetAsnAvg() {
		assertTrue(student.getAsnAvg() == 90.0);
	}

	@Test
	public void testGetExmAvg() {
		assertTrue(student.getExmAvg() == 80.0);
	}

	@Test
	public void testGetNumGrades() {
		assertTrue(student.getNumGrades() == 2);
	}

	@Test
	public void testSetNameFirst()
	{
		student.setNameFirst("Jane");
		assertTrue(student.getNameFirst().equals("Jane"));
	}

	@Test
	public void testSetNameLast()
	{
		student.setNameLast("Smith");
		assertTrue(student.getNameLast().equals("Smith"));
	}

	@Test
	public void testSetNumber()
	{
		student.setNumber("250555555");
		assertTrue(student.getNumber().equals("250555555"));
	}

	@Test
	public void testSetEmail()
	{
		student.setEmail("js@uwo.ca");
		assertTrue(student.getEmail().equals("js@uwo.ca"));
	}

	@Test
	public void testSetAvg()
	{
		student.setAvg(100.0);
		assertTrue(student.getAvg() == 100.0);
	}

	@Test
	public void testSetAsnAvg()
	{
		student.setAsnAvg(75.0);
		assertTrue(student.getAsnAvg() == 75.0);
	}

	@Test
	public void testSetExmAvg()
	{
		student.setExmAvg(50.0);
		assertTrue(student.getExmAvg() == 50.0);
	}

	@Test
	public void testAddGrade()
	{
		assertTrue(student.addGrade(0, 100.0, "Other", 1));
	}

	@Test
	public void testRemoveGrade()
	{
		assertTrue(student.removeGrade(0, "Exam"));
	}

	@Test
	public void testEqualsStudent()
	{
		assertTrue(student.equals(new Student("Joe", "Johns", "123456789", "jj@uwo.ca")));
		assertFalse(student.equals(new Student("name", "last", "987654321", "email")));
	}

	@Test
	public void testToStringGrade() {
		ArrayList<Integer> d = new ArrayList<Integer>();
		d.add(0);
		assertEquals(student.toStringGrade(d.toArray()), "\"Joe\", \"Johns\", \"123456789\", \"jj@uwo.ca\", \"80.0\"\n");
	}

	@Test
	public void testToString()
	{
		assertTrue(student.toString().equals("\"Joe\", \"Johns\", \"123456789\", \"jj@uwo.ca\"\n"));
	}
}