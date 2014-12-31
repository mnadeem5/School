public class ExceptionExample16 {

   private static boolean debug = true;
   private static final int NUM_STUDENTS = 5;
   private static final int ILLEGAL_INDEX = 5;

   /*  - try/catch in a method
       - notice where execution continues after the catch is executed
   */

   public static void change(int[] course) {

      if (debug) System.out.println("in method change ");

      try {
         course[ILLEGAL_INDEX] = 10;
      }

      catch (ArrayIndexOutOfBoundsException e) {
         System.out.println("Sorry, index " + ILLEGAL_INDEX + " does not exist.");
      }

      finally {
         System.out.println("\n in the finally block");
      }

      if (debug) System.out.println("end of method change");
   }

   public static void main (String[] args) {

      int students[] = new int[NUM_STUDENTS];

      if (debug) System.out.println("in main");
      change(students);

      if (debug) System.out.println("end of main");
   }
}