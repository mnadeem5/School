package cs2212.team4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyAvgTest {
	
	Course course1;
	MyAvg myAvg1, myAvg2, myAvg3;
	
	@Before
	public void testMyAvg() {
		course1 = new Course( "Advanced Hand Clapping", "C", "4324");
		
		course1.addStudent("Ted", "Mosby", "250565432", "tedmosby@uwo.ca");
		course1.addStudent("Barney", "Stinson", "987654321", "barneystin@uwo.ca");
		course1.addStudent("Steve", "Rogers", "123456789", "steverogers@uwo.ca");
		
		course1.addDeliverable("Project", "assignment", 20);
		course1.addDeliverable("Mid-Term", "exam", 30);
		
		course1.getStudent(0).addGrade(0, 91, "assignment", 20);
		course1.getStudent(0).addGrade(1, 63, "exam", 30);
		
		course1.getStudent(1).addGrade(0, 77, "assignment", 50);
		course1.getStudent(1).addGrade(1, 88, "exam", 50);
		
		course1.getStudent(2).addGrade(0, 55, "assignment", 50);
		course1.getStudent(2).addGrade(1, 99, "exam", 50);
		
		myAvg1 = new MyAvg( course1, course1.getStudent(0) );
		myAvg2 = new MyAvg( course1, course1.getStudent(1) );
		myAvg3 = new MyAvg( course1, course1.getStudent(2) );
	}

	@Test
	public void testCalcAvg() {
		//Assert.assertEquals( 45.80000000000001, myAvg1.calcAvg(60.0));

	}

}
