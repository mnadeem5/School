import java.io.*;

public class ExceptionExample11 {

   public static void main (String[] args) throws Exception {

      /* 
         - this handles the NumberFormatException

      */
	   boolean tryAgain=true;
      BufferedReader keyboard=
         new BufferedReader (new InputStreamReader(System.in),1);

      while (tryAgain)
    	  {
    	  System.out.print("Enter an integer: ");
    	  tryAgain=false;
    	  
      String userTyped = keyboard.readLine();

      try {
         int value = Integer.parseInt(userTyped);
      }
      catch (NumberFormatException e) {
         System.out.println("Hey, " + e.getMessage() + " is not an integer!");
         tryAgain=true;
      }
    	  }

   }
}