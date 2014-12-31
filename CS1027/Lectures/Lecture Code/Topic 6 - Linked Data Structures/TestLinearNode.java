public class TestLinearNode{
  static void print(LinearNode<Integer> head){
    LinearNode<Integer> temp;
    for (temp = head; temp != null; temp = temp.getNext())
      System.out.println(temp.getElement());
  }

  public static void main(String args[]){

    LinearNode<Integer> head = null;
    LinearNode<Integer> intNode;
		
    for (int i = 5; i >= 1; i--){
	intNode = new LinearNode<Integer>(new Integer(i));
	intNode.setNext(head);
	head = intNode;
      }
    System.out.println("head");
    print(head);

    
    LinearNode<Integer> temp;

    // we copy all the contents of "head" into "firstCopy"
    // the order will be reversed
    LinearNode<Integer> firstCopy = null;
    for(temp = head; temp != null; temp = temp.getNext()){
      intNode = new LinearNode<Integer>(temp.getElement());
      intNode.setNext(firstCopy);
      firstCopy = intNode;
    }
    System.out.println("firstCopy");
    print(firstCopy);


    // we copy all the contents of "firstCopy" into "secondCopy"
    // the order will be reversed again (so back to normal)
    LinearNode<Integer> secondCopy = null;
    for(temp = firstCopy; temp != null; temp = temp.getNext()){
      intNode = new LinearNode<Integer>(temp.getElement());
      intNode.setNext(secondCopy);
      secondCopy = intNode;
    }
    System.out.println("secondCopy");
    print(secondCopy);

    // a more tricky construction
    // we copy from "head" to "front"
    // we use "back" as a reference to the last node in the list we are building
    LinearNode<Integer> front;
    LinearNode<Integer> back;
    
    front = new LinearNode<Integer>(head.getElement());
    back = front;

    LinearNode<Integer> tmp = head.getNext();
    for (; tmp!= null; tmp = tmp.getNext()){
      intNode = new LinearNode<Integer>(tmp.getElement());
      back.setNext(intNode); // insertion at the end
      back = intNode; // update the reference to the last pointer
    }

    System.out.println("front");
    print(front);
    
  }
}