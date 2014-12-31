import java.io.*;

public class ExceptionExample15 {

   public static void main (String[] args) throws Exception {

      /* 
         - the finally block
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
      catch (ArithmeticException e) {
         System.out.println("I don't know how to divide by 0.");
      }

      finally {
         System.out.println("\nCode in the finally block is always executed");
      }

      System.out.println("end of main");
  }
}