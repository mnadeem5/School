public class ExceptionExample5 {

   public static void main (String[] args) {

      /* 
         - try must be followed immediately by at least one catch clause
         - this will generate a syntax error
      */

      final int NUM_STUDENTS = 5;

      int students[] = new int[NUM_STUDENTS];

      try {
         students[NUM_STUDENTS] = 1;
      }
      
      System.out.println("this is in between the try and the catch");

      catch {
         System.out.println("Sorry, the array element " + NUM_STUDENTS + 
                            " does not exist.");
      }

   }
}