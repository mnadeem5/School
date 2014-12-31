package cs2212.team4;

import java.util.*;
import org.junit.Test;
import org.junit.Before;

public class EmailTest {
	
	
	Course course1, course2;
	Student stud1, stud2;
	Email email1, email2;
	Properties prop1, prop2;
	
	@Before
	public void createProperties (){
		
	}
	
	@Before
	public void testEmail ()
	{
		course1 = new Course( "Classical Racism 101", "A", "2304");
		course1.addStudent("Ted", "Mosby", "250565432", "tedmosby@uwo.ca");
		
		course1.addDeliverable("Project", "assignment", 24);
		course1.addDeliverable("Projecttwo", "assignment", 30);
		course1.addDeliverable("Midterm", "exam", 46);
		
		course1.getStudent(0).addGrade(0, 97, "assignment", 24);
		course1.getStudent(0).addGrade(1, 50, "exam", 46);
		course1.getStudent(0).addGrade(2, 76, "assignment", 30);
		
		String username = "softengteam4@hotmail.com";
		String password = "Apple1234";
		prop1 = new Properties();
		
		
		prop1.put("mail.smtp.host", "smtp.live.com");
		prop1.put("mail.smtp.auth", "true");
		prop1.put("mail.smtp.starttls.enable", "true");
		prop1.put("mail.smtp.port", "587");
		prop1.put("smtp.username", username);
		prop1.put("smtp.password", password);
		
		email1 = new Email( prop1);
		email2 = new Email( course1, course1.getStudent(0), "Ted Mosby's Grades", "Here are your grades good sir", true, prop1);
		
	}
	
	@Test
	public void testgetSession ()
	{
		
	}
	
	@Test
	public void testloadProperties ()
	{

	}
	
	@Test
	public void testsendMessage ()
	{
	
	}
	
	@Test
	public void testgenerateReport ()
	{
	
	}
	
	@Test
	public void testsendEmail ()
	{
	
	}
}