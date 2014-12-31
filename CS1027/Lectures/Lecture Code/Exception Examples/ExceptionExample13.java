import java.io.*;

public class ExceptionExample13 {

   public static void main (String[] args) throws Exception {

      /* 
         - multiple exceptions
         - Exception is the most general class of exceptions.
           It's better to use the more specific classes; but
           Exception can be useful when you don't know what
           exception might be encountered.

      */

      BufferedReader keyboard=
         new BufferedReader (new InputStreamReader(System.in),1);

      System.out.print("Enter an integer: ");
      String userTyped = keyboard.readLine();

      try {
         int value = Integer.parseInt(userTyped);
      }
      catch (NumberFormatException e) {
         System.out.println("Hey, that's not an integer!");
      }
      
      try {
         System.out.println("\nDivide by zero " + 5/0);
      }
      catch (Exception e) {
         System.out.println("Exception: " + e.getMessage());
      }

      System.out.println("end of main"); 
  }
}