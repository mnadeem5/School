/**
 * Call Frame demonstration program 2
 * @author CS1027
 */

public class CallFrameDemo2
{
	private static void printAll(String s1, String s2, String s3){
		System.out.println(s1.toString());
		System.out.println(s2.toString());
		System.out.println(s3.toString());
        	return;
	}

	public static void main(String args[]) 	{
		String str1, str2, str3;

		str1 = new String(" string 1 ");
		str2 = new String(" string 2 ");
		str3 = new String(" string 3 ");

		printAll(str1, str2, str3);

	}
}
