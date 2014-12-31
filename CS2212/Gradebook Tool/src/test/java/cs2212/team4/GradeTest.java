package cs2212.team4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GradeTest
{
	Grade gr1;

	@Before
	public void testGrade()
	{
		gr1=new Grade(97, 0.5);
	}

	@Test
	public void testGetGrade()
	{
		Assert.assertTrue(gr1.getGrade()==97);
	}

	@Test
	public void testGetWeight() 
	{
		Assert.assertTrue(gr1.getWeight()==0.5);
	}

	@Test
	public void testSetGrade() 
	{
		gr1.setGrade(46);
		Assert.assertTrue(gr1.getGrade()==46);
	}

	@Test
	public void testSetWeight()
	{
		gr1.setWeight(0.2);
		Assert.assertTrue(gr1.getWeight()==0.2);
	}

}
