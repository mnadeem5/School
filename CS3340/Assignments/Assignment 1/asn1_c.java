
public class asn1_c
{
	private static bigInt fibo(int i) throws Exception
	{
		int n=1;
		bigInt a = new bigInt("1");
		bigInt b = new bigInt("1");
		bigInt tmp = new bigInt("");

		//performs the doubling technique
		for ( ;n*2<i; n*=2)
		{
			tmp=a;
			a=calcF2n(a, b);
			b=calcF2n1(tmp, b);
		}

		//performs the addition technique
		for( ; n<i+1; n++)
		{
			tmp=a.add(b);
			a=b;
			b=tmp;
		}
		return tmp;
	}

	private static bigInt calcF2n (bigInt a, bigInt b) throws Exception
	{
		//f(2n)=f(n)*[2*f(n+1)-f(n)]
		return (((b.multi(new bigInt("2"))).sub(a)).multi(a));
	}

	private static bigInt calcF2n1(bigInt a, bigInt b)
	{
		///f(2n+1)=f(n+1)^2+f(n)^2
		return a.square().add(b.square());
	}

	public static void main(String [] args) throws Exception
	{
		if (args.length<1)System.out.println("Wrong use! Ex. asn1_c x, where x is the fibo position!");
		else
		{
			int i=Integer.parseInt(args[0]);
			if (i<0) System.out.println("i must be 0<=i>=30");
			else
			{
				if (i==0)System.out.println(1);
				else System.out.println(fibo(i-2));
			}
		}
	}
}
