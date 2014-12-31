import java.io.*;

public class RecursionLab{
    
    public static void reversePrint (String inString)
    {
    	int ptr;
		if (inString.length() > 0)		// if string is not empty
		{	
			ptr=inString.length()-1;
			System.out.print(inString.charAt(ptr));
			reversePrint(inString.substring(0, ptr));
		}
		
    }

	    
    public static void main(String[] args){
        String inString = "abcde";

		// test reversePrint
		reversePrint(inString);		
    }
}
