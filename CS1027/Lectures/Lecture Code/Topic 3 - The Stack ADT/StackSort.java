import java.util.Scanner;
import java.util.Random;

public class StackSort {

  public static void main (String[] args) {

     final int MAX = 100 ;
     final int MAX_INT = 1000 ;
     int[] numbers ;
     ArrayStack<Integer> sortedStack ;
     ArrayStack<Integer> tempStack ;

     Random generator = new Random() ;
     numbers = new int[MAX] ;

     sortedStack = new ArrayStack<Integer>(MAX) ;
     tempStack = new ArrayStack<Integer>(MAX) ;

     for (int i = 0 ; i < MAX ; i++) {
        numbers[i] = generator.nextInt(MAX_INT) ;
        System.out.println(numbers[i]) ;
    }

    sortedStack.push(numbers[0]) ;

    for (int i = 1 ; i < MAX ; i++) {
       while (!sortedStack.isEmpty() && numbers[i] <= sortedStack.peek()) {
          tempStack.push(sortedStack.pop()) ;
        }
        sortedStack.push(numbers[i]) ;
        while (!tempStack.isEmpty()) {
            sortedStack.push(tempStack.pop()) ;
        }
    }

    System.out.println("Stack Size = " + sortedStack.size()) ;
    System.out.println("Sorted numbers: \n" + sortedStack.toString()) ;
  }
}
