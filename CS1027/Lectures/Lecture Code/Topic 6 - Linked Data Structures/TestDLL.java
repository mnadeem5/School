public class TestDLL{
  public static void main(String[] args){
    // create a DoublyLinkedList with a single node
    DoublyLinkedList<Integer>
      head = new DoublyLinkedList<Integer>(new Integer(0));
    DoublyLinkedList<Integer> tail = head;
    
    // we want to insert 1 at the head
    DoublyLinkedList<Integer> intNode = 
      new DoublyLinkedList<Integer>(new Integer(1));
    intNode.setNext(head);
    head.setPrevious(intNode); // this is new w.r.t. singly linked lists
    head = intNode;

    System.out.println(head.getElement()); // front element
    System.out.println(head.getNext().getElement()); // next element
    System.out.println(head.getNext().getPrevious().getElement()); // front element again
  }
}
