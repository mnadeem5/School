
public class SavingsAccount extends BankAccount
{
	private Double intialAmount, rate;
	public SavingsAccount(double intialAmount, double rate)
	{
		super(intialAmount);
		this.rate=rate;
	}
	public Double getRate()
	{
		return this.rate;
	}
	public void calculateIntrest()
	{
		double intrest;
		intrest = this.rate*this.intialAmount;
		this.intialAmount+=intrest;
	}
	public String toString()
	{
		String s="Your interest rate is "+this.rate+"$, and your saving account is currently holding "+this.intialAmount+"$";
		return s;
	}
	
}
