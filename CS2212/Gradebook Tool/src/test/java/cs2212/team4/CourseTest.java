package cs2212.team4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.util.ArrayList;
import java.io.File;

public class CourseTest
{
	Course crs1, crs2, crs3, crs4, crs5, crs6;
	Student stud, stud2;
	Deliverable deliver, deliver2;
	ArrayList<Student> studList;
	ArrayList<Deliverable> deliverList;
	
	@Before
	public void testCourse()
	{
		stud=new Student("Marry", "Poppins", "250555000", "mpoppins");
		deliver=new Deliverable("Project", "assignment", 54, 0);
		stud2=new Student("John", "Johnson", "250222444", "jjohn22");
		deliver2=new Deliverable("Midterm", "exam", 46, 0);
		studList= new ArrayList<Student>();
		deliverList= new ArrayList<Deliverable>();
		studList.add(new Student("John", "Johnson", "250222444", "jjohn22"));
		deliverList.add(new Deliverable("Midterm", "exam", 46, 0));
		
		//A course with students, grades, description, assignment deliverable, exam deliverable
		crs1=new Course("English", "B", "2121");
		crs1.setDescription("A course in English");
		crs1.addStudent("Marry", "Poppins", "250555000", "mpoppins");
		crs1.addStudent("John", "Johnson", "250222444", "jjohn22");
		crs1.addDeliverable("Project", "assignment", 54);
		crs1.addDeliverable("Midterm", "exam", 46);
		crs1.getStudent(0).addGrade(0, 97, "assignment", 54);
		crs1.getStudent(1).addGrade(0, 95, "assignment", 54);
		crs1.getStudent(0).addGrade(1, 50, "exam", 46);
		crs1.getStudent(1).addGrade(1, 62, "exam", 46);
		
		//A course with only a description
		crs2=new Course("Computer Science", "A", "2208");
		crs2.setDescription("A course in Computer Science");
		
		//A course with a student with no assigned grade, no description, exam deliverable
		crs3=new Course("Philosophy", "B", "1022");
		crs3.addStudent("John", "Johnson", "250222444", "jjohn22");
		crs3.addDeliverable("Midterm", "exam", 25);
		
		//A course with only a student
		crs4=new Course("Geology", "A", "4011");
		crs4.addStudent("John", "Johnson", "250222444", "jjohn22");
				
		//A course with only an exam deliverable
		crs5=new Course("Scientology", "A", "4020");
		crs5.addDeliverable("Midterm", "exam", 25);
		
		//A copy of course 5
		crs6=new Course("Scientology", "A", "4020");
		crs6.addDeliverable("Midterm", "exam", 25);
		
		crs6=new Course("Scientology", "A", "4020");
		crs6.addDeliverable("Midterm", "exam", 46);
		
	}

	@Test
	public void testGetTitle()
	{
		Assert.assertTrue(crs1.getTitle().equals("English"));
		Assert.assertFalse(crs1.getTitle().equals(crs2.getTitle()));
	}

	@Test
	public void testGetTerm()
	{
		Assert.assertTrue(crs1.getTerm().equals("B"));
		Assert.assertFalse(crs1.getTerm().equals(crs2.getTerm()));
	}

	@Test
	public void testGetCode()
	{
		Assert.assertTrue(crs1.getCode().equals("2121"));
		Assert.assertFalse(crs1.getCode().equals(crs2.getCode()));
	}

	@Test
	public void testGetColor()
	{
		Assert.assertTrue(crs1.getColor().equals(new Color(20,150,250)));
		Assert.assertFalse(crs1.getColor().equals(new Color(0,0,0)));
	}

	@Test
	public void testGetDescription() 
	{
		Assert.assertTrue(crs1.getDescription().equals("A course in English"));
		Assert.assertFalse(crs1.getDescription().equals(crs2.getDescription()));
		Assert.assertTrue(crs3.getDescription().equals(""));
	}

	@Test
	public void testGetStudent()
	{
		Assert.assertTrue(crs1.getStudent(2)==null);
		Assert.assertTrue(crs1.getStudent(-1)==null);
		Assert.assertTrue(crs1.getStudent(0).equals(stud));
		Assert.assertFalse(crs1.getStudent(0).equals(crs3.getStudent(0)));
	}

	@Test
	public void testGetDeliverable()
	{
		Assert.assertTrue(crs1.getDeliverable(2)==null);
		Assert.assertTrue(crs1.getDeliverable(-1)==null);
		Assert.assertTrue(crs1.getDeliverable(0).equals(deliver));
		Assert.assertFalse(crs1.getDeliverable(0).equals(crs3.getDeliverable(0)));
	}

	@Test
	public void testGetGrade() 
	{
		Assert.assertTrue(crs1.getGrade(stud,2)==-1);
		Assert.assertTrue(crs1.getGrade(stud,-1)==-1);
		Assert.assertTrue(crs1.getGrade(crs1.getStudent(0),0)==97);
	}

	@Test
	public void testGetClassAvg()
	{
		//Average for a course with deliverables, students, and grades
		Assert.assertTrue(crs1.getClassAvg()==77.6);
		//Average for a course with no students or deliverables
		Assert.assertTrue(crs2.getClassAvg()==-1);
		//Average for a course with deliverables but no students
		Assert.assertTrue(crs4.getClassAvg()==-1);
		//Average for a course with students but no deliverables
		Assert.assertTrue(crs5.getClassAvg()==-1);
		//Average for a course with deliverables and students, but no grades
		Assert.assertTrue(crs3.getClassAvg()==-1);
	}

	@Test
	public void testGetClassAsnAvg()
	{
		//Assignment average for a course with an assignment deliverable, students, and grades
		Assert.assertTrue(crs1.getClassAsnAvg()==96);
		//Assignment average for a course with no students or deliverables
		Assert.assertTrue(crs2.getClassAsnAvg()==-1);
		//Assignment average for a course with assignment deliverables but no students
		Assert.assertTrue(crs4.getClassAsnAvg()==-1);
		//Assignment average for a course with students but no deliverables
		Assert.assertTrue(crs5.getClassAsnAvg()==-1);
		//Assignment average for a course with deliverables and students, but no grades
		Assert.assertTrue(crs3.getClassAsnAvg()==-1);
	}

	@Test
	public void testGetClassExamAvg()
	{
		//Exam average for a course with an exam deliverable, students, and grades
		Assert.assertTrue(crs1.getClassExamAvg()==56);
		//Exam average for a course with no students or deliverables
		Assert.assertTrue(crs2.getClassExamAvg()==-1);
		//Exam average for a course with exam deliverables but no students
		Assert.assertTrue(crs4.getClassExamAvg()==-1);
		//Exam average for a course with students but no deliverables
		Assert.assertTrue(crs5.getClassExamAvg()==-1);
		//Exam average for a course with deliverables and students, but no grades
		Assert.assertTrue(crs3.getClassExamAvg()==-1);
	}

	@Test
	public void testGetClassDeliverableAvg()
	{
		//Average for a course with deliverables, students, and grades
		Assert.assertTrue(crs1.getClassDeliverableAvg(0)==96);
		//Average for a course with no students or deliverables
		Assert.assertTrue(crs2.getClassDeliverableAvg(0)==-1);
		//Average for a course with deliverables but no students
		Assert.assertTrue(crs4.getClassDeliverableAvg(0)==-1);
		//Average for a course with students but no deliverables
		Assert.assertTrue(crs5.getClassDeliverableAvg(0)==-1);
		//Average for a course with deliverables and students, but no grades
		Assert.assertTrue(crs3.getClassDeliverableAvg(0)==-1);
		//Try to get a deliverable that's out of bounds
		Assert.assertTrue(crs3.getClassDeliverableAvg(3)==-1);
		
		crs3.removeDeliverable(0);
		Assert.assertTrue(crs3.getClassDeliverableAvg(0)==-1);
	}

	@Test
	public void testGetDeliverableListSize()
	{
		Assert.assertTrue(crs1.getDeliverableListSize()==2);
	}

	@Test
	public void testGetStudentListSize()
	{
		Assert.assertTrue(crs1.getStudentListSize()==2);
	}

	@Test
	public void testGetStudents()
	{
		for (int i=0; i<studList.size(); i++){
			Assert.assertTrue(crs4.getStudents().get(i).equals(studList.get(i)));
		}
	}

	@Test
	public void testGetDeliverables()
	{
		for (int i=0; i<deliverList.size(); i++){
			Assert.assertTrue(crs6.getDeliverables().get(i).equals(deliverList.get(i)));
		}
	}

	@Test
	public void testSetTitle()
	{
		 crs5.setTitle("New title");
		 Assert.assertTrue(crs5.getTitle().equals("New title"));
	}

	@Test
	public void testSetTerm()
	{
		crs5.setTerm("New term");
		Assert.assertTrue(crs5.getTerm().equals("New term"));
	}

	@Test
	public void testSetCode()
	{
		crs5.setCode("z");
		Assert.assertTrue(crs5.getCode().equals("z"));
	}

	@Test
	public void testSetColor()
	{
		crs5.setColor(new Color(100,100,100));
		Assert.assertTrue(crs5.getColor().equals(new Color(100,100,100)));
	}

	@Test
	public void testSetDescription()
	{
		crs5.setDescription("A new description");
		Assert.assertTrue(crs5.getDescription().equals("A new description"));
	}
	
	@Test
	public void testSetTotalWeight()
	{
		crs1.setTotalWeight(110);
		Assert.assertTrue(crs1.getTotalWeight()==110);
	}

	@Test
	public void testEditStudentNumber()
	{
		//Change to the same number
		Assert.assertTrue(crs1.editStudentNumber(stud,"250555000").equals(""));
		//Change to a new number
		Assert.assertTrue(crs1.editStudentNumber(stud,"000222000").equals(""));
		//Try setting to a number already belonging to a student
		Assert.assertTrue(crs1.editStudentNumber(stud,"250222444").equals("The Student Number already exists"));
	}

	@Test
	public void testEditStudentEmail()
	{
		//Change to the same email
		Assert.assertTrue(crs1.editStudentEmail(stud,"mpoppins").equals(""));
		//Change to a new email
		Assert.assertTrue(crs1.editStudentEmail(stud,"poppy777").equals(""));
		//Try setting to a email already belonging to a student
		Assert.assertTrue(crs1.editStudentEmail(stud,"jjohn22").equals("The Student Email already exists"));
	}

	@Test
	public void testFindStudent()
	{
		Assert.assertTrue(crs1.findStudent("250555000")==0);
		Assert.assertTrue(crs1.findStudent("250222444")==1);
		//Find a non existing student
		Assert.assertTrue(crs1.findStudent("0909090909")==-1);
	}

	@Test
	public void testAddStudent()
	{
		Assert.assertTrue(crs1.addStudent("Jimmy", "Falucci", "9876543210", "jfalucci"));
		//Try adding a student with an existing email
		Assert.assertFalse(crs1.addStudent("Copy", "Cat", "number", "mpoppins"));
		//Try adding a student with an existing number
		Assert.assertFalse(crs1.addStudent("Copy", "Cat", "250222444", "email"));
	}

	@Test
	public void testRemoveStudent()
	{
		Assert.assertTrue(crs4.removeStudent(0));
		//Try removing a student from an index that is out of bounds
		Assert.assertFalse(crs1.removeStudent(3));
	}

	@Test
	public void testFindDeliverable()
	{
		Assert.assertTrue(crs1.findDeliverable(deliver)==0);
		//Find a non existing deliverable
		Assert.assertTrue(crs3.findDeliverable(deliver)==-1);
	}

	@Test
	public void testAddDeliverable()
	{
		//Add existing deliverable
		Assert.assertTrue(crs1.addDeliverable("Project", "assignment", 54).equals("Deliverable already exists"));
		//Add a deliverable that exceeds total weight
		Assert.assertTrue(crs1.addDeliverable("Final exam", "exam", 98).equals("You have exceeded the course 100% weight \nceiling, current usable weight "
					                                                            + (100 - crs1.getTotalWeight()) + "%"));
		//Add a deliverable to a course with pre-existing deliverables
		Assert.assertTrue(crs5.addDeliverable("New project", "assignment", 20).equals(""));
		//Add a deliverable to a course without pre-existing deliverables
		Assert.assertTrue(crs2.addDeliverable("Project 2", "assignment", 20).equals(""));
		
		crs2.removeDeliverable(crs2.getDeliverableListSize()-1);
		
		Assert.assertTrue(crs2.addDeliverable("Final Assignment", "assignment", 5).equals(""));
	}

	@Test
	public void testRemoveDeliverable()
	{
		Assert.assertTrue(crs1.removeDeliverable(0));
		//Try to remove a deliverable outside index
		Assert.assertFalse(crs1.removeDeliverable(2));
	}
	
	@Test
	public void testImportStudents()
	{
		File file = new File("gradebook-files/testFiles/Students.csv");
		Assert.assertTrue(crs1.importStudents(file)=="");
		//No first name column
		file = new File("gradebook-files/testFiles/StudentsNoFirst.csv");
		Assert.assertTrue(crs1.importStudents(file)=="Error: First Name column was not found");
		//No last name column
		file = new File("gradebook-files/testFiles/StudentsNoLast.csv");
		Assert.assertTrue(crs1.importStudents(file)=="Error: Last Name column was not found");
		//No last name column
		file = new File("gradebook-files/testFiles/StudentsNoNumber.csv");
		Assert.assertTrue(crs1.importStudents(file)=="Error: Student Number column was not found");
		//No last name column
		file = new File("gradebook-files/testFiles/StudentsNoEmail.csv");
		//Assert.assertTrue(crs1.importStudents(file)=="Error: Email column was not found");
		
		//File not found exception
		file = new File("gradebook-files/testFiles/Studentszzzzzz.csv");
		Assert.assertTrue(crs1.importStudents(file)=="Error: File not found");
	}

	@Test
	public void testExportStudents()
	{
		File file = new File("gradebook-files/testFiles/Students.csv");
		if (file.exists())
			file.delete();
		Assert.assertTrue(crs4.exportStudents(file)=="");
		
		//Fail to export
		file = new File("gradebook-files/testFiles/nonExistingDirectory/Students.csv");
		if (file.exists())
			file.delete();
		Assert.assertTrue(crs4.exportStudents(file)=="Error: program failed to export the file");
	}

	@Test
	public void testImportDeliverables() 
	{
		File file = new File("gradebook-files/testFiles/Deliverables.csv");
		Assert.assertTrue(crs1.importDeliverables(file)=="");
		//No name
		file = new File("gradebook-files/testFiles/DeliverablesNoName.csv");
		Assert.assertTrue(crs1.importDeliverables(file)=="Error: Name column was not found");
		//No type
		file = new File("gradebook-files/testFiles/DeliverablesNoType.csv");
		Assert.assertTrue(crs1.importDeliverables(file)=="Error: Type column was not found");
		//No weight
		file = new File("gradebook-files/testFiles/DeliverablesNoWeight.csv");
		Assert.assertTrue(crs1.importDeliverables(file)=="Error: Weight column was not found");
		
		//File not found exception
		file = new File("gradebook-files/testFiles/Deliverableszzzzzz.csv");
		Assert.assertTrue(crs1.importStudents(file)=="Error: File not found");
	}

	@Test
	public void testExportDeliverables()
	{
		File file = new File("gradebook-files/testFiles/Deliverables.csv");
		if (file.exists())
			file.delete();
		Assert.assertTrue(crs1.exportDeliverables(file)=="");
		
		file = new File("gradebook-files/testFiles/DeliverablesEmpty.csv");
		if (file.exists())
			file.delete();
		Assert.assertTrue(crs1.exportDeliverables(file)=="");
		
		//Fail to export
		file = new File("gradebook-files/testFiles/nonExistingDirectory/Deliverables.csv");
		if (file.exists())
			file.delete();
		Assert.assertTrue(crs4.exportStudents(file)=="Error: program failed to export the file");
	}

	@Test
	public void testImportGrades() 
	{
		File file = new File("gradebook-files/testFiles/Grades.csv");
		Assert.assertTrue(crs1.importGrades(file).equals(""));
		
		//No number
		file = new File("gradebook-files/testFiles/GradesNoNumber.csv");
		Assert.assertTrue(crs1.importGrades(file)=="Error: Student Number column was not found");
		
		//File not found exception
		file = new File("gradebook-files/testFiles/Gradeszzzzzz.csv");
		Assert.assertTrue(crs1.importStudents(file)=="Error: File not found");
	}

	@Test
	public void testExportGrades()
	{
		File file = new File("gradebook-files/testFiles/Grades.csv");
		if (file.exists())
			file.delete();
		Assert.assertTrue(crs1.exportGrades(file)=="");
		
		//Fail to export
		file = new File("gradebook-files/testFiles/nonExistingDirectory/Grades.csv");
		if (file.exists())
			file.delete();
		Assert.assertTrue(crs4.exportStudents(file)=="Error: program failed to export the file");
	}
	
	@Test
	public void testEqualsCourse()
	{
		Assert.assertTrue(crs5.equals(crs6));
		Assert.assertFalse(crs1.equals(crs2));
	}

	@Test
	public void testToString()
	{
		Assert.assertTrue(crs1.toString().equals("\"English\", \"B\", \"2121\"\n"));
	}

}
