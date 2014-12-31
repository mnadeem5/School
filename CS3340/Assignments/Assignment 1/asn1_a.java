
public class asn1_a
{
	private static int fibo(int i)
	{
		if (i<2)return i;
		else return (fibo(i-1)+fibo(i-2));
	}

	public static void main(String [] args)
	{
		if (args.length<1)System.out.println("Wrong use! Ex. asn1_b x, where x*5 is the fibo position!");
		else
		{
			int i=Integer.parseInt(args[0]);;
			if (i<0||i>7) System.out.println("x must be 0<=x>=7");
			else System.out.println(fibo(i*5));
		}
	}
}
