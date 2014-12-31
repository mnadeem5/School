
public class test {
	   //--------------------------------------------------------------------
	   // Test harness for Linked Stack
	   //--------------------------------------------------------------------
	   public static void main(String[] args)
	   {
	     LinkedStack<String> stack = new LinkedStack<String>();
	     String onTop;

	     stack.push("A");
	     stack.push("B");
	     stack.push("C");
	     stack.push("D");

	     // test size and toString
	     //System.out.println("stack contains "+ stack.size() + " items: \n" + stack.toString());

	     // test isEmpty
	     //while (! stack.isEmpty())
	     //{
	     //     onTop = stack.pop();
	     //     System.out.println("popped item is " + onTop);
	     //}
	     System.out.print(stack.iterator().toString());
	  }
}
