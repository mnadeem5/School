/**
 * Call Frame demonstration program 1
 * @author CS1027
 */

public class CallFrameDemo1 
{
	public static double square(double n){
		double temp;
		temp = n * n;
		return temp;
	}

	public static void main(String args[]) 	{
		double x = 4.5;
		double y;
		y = square(x);
		System.out.println("Square of " + x+ " is " + y);
	}
}
