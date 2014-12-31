package cs2212.team4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentReportDataTest {
	
	Course course1, course2, course3;
	StudentReportData srd1, srd2, srd3, srd4, srd5, srd6, srd7, srd8;
	
	@Before
	public void createStudentReportData() {
		course1 = new Course ( "Wheels 101", "B", "1567");
		course2 = new Course ( "Advanced Quantum Physics", "E", "4444");
		course3 = new Course ( "Intermediate Secret Keeping", "G", "2678");
		course1.addStudent("Ted", "Mosby", "250565432", "tedmosby@uwo.ca");
		course1.addStudent("Barney", "Stinson", "987654321", "barneystin@uwo.ca");
		course2.addStudent("Barney", "Stinson", "987654321", "barneystin@uwo.ca");
		course3.addStudent("Lily", "Aldrin", "123456789", "lilypad@uwo.ca");
		
		//course1 has 2 students, 2 assignments and 1 exam
		course1.addDeliverable("Project", "assignment", 24);
		course1.addDeliverable("Projecttwo", "assignment", 30);
		course1.addDeliverable("Midterm", "exam", 46);
		course1.getStudent(0).addGrade(0, 97, "assignment", 24);
		course1.getStudent(1).addGrade(0, 95, "assignment", 24);
		course1.getStudent(0).addGrade(1, 50, "exam", 46);
		course1.getStudent(1).addGrade(1, 62, "exam", 46);
		course1.getStudent(0).addGrade(2, 76, "assignment", 30);
		course1.getStudent(1).addGrade(2, 82, "assignment", 30);
		
		//course2 has 2 students, 1 assignment and 1 exam
		course2.addDeliverable("Project", "assignment", 54);
		course2.addDeliverable("Midterm", "exam", 23);
		course2.addDeliverable("Final", "exam", 23);
		course2.getStudent(0).addGrade(0, 97, "assignment", 54);
		course2.getStudent(0).addGrade(1, 57, "exam", 23);
		course2.getStudent(0).addGrade(2, 70, "exam", 23);
		
		srd1 = new StudentReportData ( course1.getStudent(0), course1, -1);
		srd2 = new StudentReportData ( course1.getStudent(0), course1, 1);
		srd3 = new StudentReportData ( course1.getStudent(0), course1, -4);
		
		srd4 = new StudentReportData ( course2.getStudent(0), course2, -1);
		srd5 = new StudentReportData ( course2.getStudent(0), course2, 1);
		
		// course3 has 1 student and 1 deliverable
		course3.addDeliverable("Exam", "exam", 100);
		course3.getStudent(0).addGrade(0, 85, "exam", 100);
		srd6 = new StudentReportData (course3.getStudent(0), course3, -1);
		srd7 = new StudentReportData (course3.getStudent(0), course3, 0);
	}

	@Test
	public void testGetCourseTitle() {
		// if i is not greater than -1, should return title 
		Assert.assertSame( "Wheels 101", srd1.getCourseTitle() );
		// if i is greater than -1, should return null
		Assert.assertSame( "", srd2.getCourseTitle() );
	}

	@Test
	public void testGetCourseTerm() {
		// if i is not greater than -1, should return title 
		Assert.assertSame( "B", srd1.getCourseTerm() );
		// if i is greater than -1, should return null
		Assert.assertSame( "", srd2.getCourseTerm() );
	}

	@Test
	public void testGetCourseCode() {
		// if i is not greater than -1, should return course code
		Assert.assertSame( "1567", srd1.getCourseCode() );
		// if i is greater than -1, should return null
		Assert.assertSame( "", srd2.getCourseCode() );
	
	}

	@Test
	public void testGetClassAvg() {
		// if i is not greater than -1, should return class average
		Assert.assertTrue( 72.5==srd1.getClassAvg() );
		// if i is greater than -1, should return null
		Assert.assertTrue(-1==srd2.getClassAvg() );
	}

	@Test
	public void testGetClassAsnAvg() {
		// if i is not greater than -1, should return class assignment average
		Assert.assertTrue(86.55555555555554==srd1.getClassAsnAvg() );
		// if i is greater than -1, should return null
		Assert.assertTrue( -1==srd2.getClassAsnAvg() );
	
	}

	@Test
	public void testGetClassExamAvg() {
		// if i is not greater than -1, should return class exam average
		Assert.assertTrue ( 56.0==srd1.getClassExamAvg() );
		// if i is greater than -1, should return null
		Assert.assertTrue( -1==srd2.getClassExamAvg() );
	
	}

	@Test
	public void testGetStudentFirstName() {
		// if i is not greater than -1, should return student first name
		Assert.assertSame ( "Ted", srd1.getStudentFirstName() );
		// if i is greater than -1, should return null
		Assert.assertSame ( "", srd2.getStudentFirstName() );
	
	}

	@Test
	public void testGetStudentLastName() {
		// if i is not greater than -1, should return student last name
		Assert.assertSame ("Mosby", srd1.getStudentLastName() );
		// if i is greater than -1, should return null
		Assert.assertSame ( "", srd2.getStudentLastName() );
	}

	@Test
	public void testGetStudentNumber() {
		// if i is not greater than -1, should return student number
		Assert.assertSame ("250565432", srd1.getStudentNumber() );
		// if i is greater than -1, should return null
		Assert.assertSame ( "", srd2.getStudentNumber() );
	}

	@Test
	public void testGetStudentEmail() {
		// if i is not greater than -1, should return student number
		Assert.assertSame ("tedmosby@uwo.ca", srd1.getStudentEmail() );
		// if i is greater than -1, should return null
		Assert.assertSame ( "", srd2.getStudentEmail() );
	}

	@Test
	public void testGetStudentAvg() {
		// if i is not greater than -1, should return student average
		Assert.assertTrue ( 69.08==srd1.getStudentAvg() );
		// if i is greater than -1, should return null
		Assert.assertTrue ( -1==srd2.getStudentAvg() );
	}

	@Test
	public void testGetStudentAsnAvg() {
		// if i is not greater than -1, should return student assignment average
		Assert.assertTrue ( 85.33333333333333==srd1.getStudentAsnAvg() );
		// if i is greater than -1, should return null
		Assert.assertTrue ( -1==srd2.getStudentAsnAvg() );
		
	}

	@Test
	public void testGetStudentExamAvg() {
		// if i is not greater than -1, should return student exam average
		Assert.assertTrue ( 63.5==srd4.getStudentExamAvg() );
		// if i is greater than -1, should return null
		Assert.assertTrue ( -1==srd5.getStudentExamAvg() );
	}

	@Test
	public void testGetGrade() {
		// if i is greater than -1 should return grade
		Assert.assertTrue ( 85==srd7.getGrade() );
		// if i is equal to -1 should return -1
		Assert.assertTrue ( -1==srd6.getGrade() );
	}

	@Test
	public void testGetWeight() {
		// if i is greater than -1 should return weight
		Assert.assertTrue ( 100==srd7.getWeight() );
		// if i is equal to -1 should return -1
		Assert.assertTrue ( -1==srd6.getWeight() );
	}

	@Test
	public void testGetDeliverAvg() {
		// if i is greater than -1 should return deliverable average
		Assert.assertTrue ( 85==srd7.getDeliverAvg() );
		// if i is equal to -1 should return -1
		Assert.assertTrue ( -1==srd6.getDeliverAvg() );
	}

	@Test
	public void testGetDeliverName() {
		// if i is greater than -1 should return deliverable name
		Assert.assertSame ( "Exam", srd7.getDeliverName() );
		// if i is equal to -1 should return -1
		Assert.assertSame ( "", srd6.getDeliverName() );
	}

	@Test
	public void testGetDeliverType() {
		// if i is greater than -1 should return deliverable type
		Assert.assertSame ( "exam", srd7.getDeliverType() );
		// if i is equal to -1 should return -1
		Assert.assertSame ( "", srd6.getDeliverType() );
	}

}
