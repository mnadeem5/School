public class ExceptionExample4 {

   public static void main (String[] args) {

      /* 
         - a catch construct cannot appear without a try construct
         - this will generate a syntax error
      */

      final int NUM_STUDENTS = 5;

      int students[] = new int[NUM_STUDENTS];

      catch (ArrayOutOfBoundsException e) {
         System.out.println("Sorry, the array element " + NUM_STUDENTS + 
                            " does not exist.");
      }

   }
}