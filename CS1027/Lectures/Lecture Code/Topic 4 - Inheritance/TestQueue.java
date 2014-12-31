import java.util.* ;

public class TestQueue {

    public static void main(String[] args) {

        ArrayQueue<Integer> Queue ;

        Queue = new ArrayQueue<Integer>() ;

        for (Integer i = 0 ; i < 10 ; i++) {
            Queue.enqueue(i) ;
            System.out.println(Queue.toString()) ;
        }
        System.out.println("Queue size is : " + Queue.size() + "\n") ;
        System.out.println("Last element is : " + Queue.last() + "\n") ;
        System.out.println("First element is : " + Queue.first() + " \n") ;

        while (!Queue.isEmpty()) {
            Queue.dequeue() ;
            System.out.println(Queue.toString()) ;
        }
    }
}
