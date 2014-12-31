import java.io.*;

/**
 * Demonstration of iterative and recursive Factorial
 * @author CS027
 */

public class FactorialDemo 
{

	/** Method to calculate n factorial recursively
	 * @param n an integer >= 1 
	 * @return n factorial
	 * precondition: n >= 1
	 */

	public static int rfact (int n)
	{
		if (n ==1)
                    return 1;
		else
                    return n * rfact(n-1);
	}


	/** Method to calculate n factorial iteratively
	 * @param n an integer >= 1
	 * @return n factorial
	 * precondition: n >= 1
	 */

	public static int ifact(int n)
	{
		if (n == 1)
                    return 1;
		else 
		{
                    int product = 1;
                    for (int i = 1; i <= n; i ++) 
			{
			product = product * i;
			}
                    return product;
		}
	}

	
	/** Main Method to call the recursive and iterative Factorial
	 * methods.
	 */

	public static void main(String args[]) throws Exception
	{
		BufferedReader keyboard = new BufferedReader
			(new InputStreamReader(System.in),1);
		
		System.out.println("Enter an integer: ");
		int n = Integer.parseInt(keyboard.readLine());
       
		System.out.println("Iterative Factorial: " + ifact(n) + "\n");
		System.out.println("Recursive Factorial: " + rfact(n) + "\n");
	}
}
