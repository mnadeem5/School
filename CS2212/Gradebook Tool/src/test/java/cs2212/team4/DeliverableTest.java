package cs2212.team4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeliverableTest
{
	Deliverable deliver1, deliver2;

	@Before
	public void testDeliverable()
	{
		deliver1=new Deliverable("Assignment 1", "assignment", 0.8, 0);
	}

	@Test
	public void testGetName()
	{
		Assert.assertTrue(deliver1.getName().equals("Assignment 1"));
	}

	@Test
	public void testGetType() 
	{
		Assert.assertTrue(deliver1.getType().equals("assignment"));
	}

	@Test
	public void testGetWeight() 
	{
		Assert.assertTrue(deliver1.getWeight()==0.8);
	}

	@Test
	public void testGetObjId()
	{
		Assert.assertTrue(deliver1.getObjId()==0);
	}

	@Test
	public void testSetName()
	{
		deliver1.setName("New name");
		Assert.assertTrue(deliver1.getName().equals("New name"));
	}

	@Test
	public void testSetType()
	{
		deliver1.setType("exam");
		Assert.assertTrue(deliver1.getType().equals("exam"));
	}

	@Test
	public void testSetWeight()
	{
		deliver1.setWeight(0.5);
		Assert.assertTrue(deliver1.getWeight()==0.5);
	}

	@Test
	public void testEqualsDeliverable()
	{
		Assert.assertTrue(deliver1.equals(new Deliverable("Assignment 1", "assignment", 0.8, 0)));
		Assert.assertFalse(deliver1.equals(new Deliverable("New name","New type",0.9, 0)));
	}

	@Test
	public void testToString()
	{
		Assert.assertTrue(deliver1.toString().equals("\"Assignment 1\", \"assignment\", \"0.8\"\n"));
	}

}
