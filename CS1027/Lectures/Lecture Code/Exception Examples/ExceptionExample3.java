public class ExceptionExample3 {

   public static void main (String[] args) {

      /* 
         - each try construct must have at least one catch or finally 
         - this code will generate a syntax error
      */

      final int NUM_STUDENTS = 5;

      int students[] = new int[NUM_STUDENTS];

      try {
         students[NUM_STUDENTS] = 1;
      }

   }
}