package ca.uwo.csd.cs2212.zalbiraw;

import junit.framework.Assert;
import org.junit.Test;
import java.lang.ArithmeticException ;
import org.junit.Before;

public class CalculatorTest 
{
	private Calculator calculator ;
	
	@Before
	public void createCalculator () 
	{
		this.calculator = new Calculator ();
	}
	
	@Test
	public void testAddTwoPositiveNumbers() 
	{
		int result = calculator.add (5, 4);
		Assert.assertEquals(9, result);
	}
	
	@Test
	public void testAddZeroAndOnePositiveNumber() 
	{
		int result = calculator.add (0, 4);
		Assert.assertEquals(4, result);
	}
	
	@Test
	public void testAddTwoNegativeNumbers ()
	{
		int result = calculator.add (-4, -5);
		Assert.assertEquals(-9, result );
	}
	@Test
	public void testAddZeroAndOneNegativeNumber () 
	{
		int result = calculator.add (0, -4);
		Assert.assertEquals(-4, result );
	}
	
	@Test (expected = ArithmeticException.class )
	public void testAddOverflow () 
	{
		calculator.add(Integer.MAX_VALUE, 1);
	}
	
	@Test ( expected = ArithmeticException.class )
	public void testAddUnderflow () 
	{
		calculator.add(Integer.MIN_VALUE,-1);
	}
}