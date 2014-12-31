public class asn1_b
{
	private static bigInt fibo(bigInt a, bigInt b, int i)
	{
		bigInt tmp = new bigInt("");

		if (i==1)return b;
		else
		{
			tmp=a.add(b);
			a=b;
			b=tmp;
			i--;
			return fibo(a, b, i);
		}
	}

	public static void main(String [] args)
	{
		//if (args.length<1)System.out.println("Wrong use! Ex. asn1_b x, where x*10 is the fibo position!");
		//else
		//{
			//int i=Integer.parseInt(args[0]);
			//if (i<0||i>30) System.out.println("x must be 0<=x>=30");
			//else
			//{
				bigInt a=new bigInt("0");
				bigInt b=new bigInt("1");
				System.out.println(fibo(a, b,45));
			//}
		//}
	}
}
