/**
 * Call Stack demonstration program 
 * @author CS1027
 */

public class CallStackDemo 
{
	public static void m2()
	{
		System.out.println("Starting m2");
		System.out.println("m2 calling m3");
		m3();
		System.out.println("m2 calling m4");
		m4();
		System.out.println("Leaving m2");
		return;
	}

	public static void m3()
	{
		System.out.println("Starting m3");
		System.out.println("Leaving m3");
		return;
	}

	public static void m4()
	{
		System.out.println("Starting m4");
		System.out.println("Leaving m4");
		return;
	}


	public static void main(String args[])
	{
		System.out.println("Starting main");
		System.out.println("main calling m2");
		m2();
		System.out.println("Leaving main");
	}
}


