package ca.uwo.csd.cs2212.zalbiraw;
 
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
 
public class TestBankAccount 
{
	@Test
	public void testDebitWithSufficientFunds()
	{
		BankAccount account = new BankAccount(10);
		double amount = account.debit(5);
		Assert.assertEquals(5.0, amount);
	}
	
	@Test
	public void testDebitWithInsufficientFunds()
	{
		BankAccount account = new BankAccount(10);
		double amount = account.debit(11);
		Assert.assertEquals(10.0, amount);
	}
}