public class MyFriends {
  public static void main(String args[]) {

    SocialNetwork contacts = new SocialNetwork();

    contacts.add("Snoopy","Dog","snoopy@uwo.ca");
    contacts.add("Felix","Cat","felix@uwo.ca");
    contacts.add("Mickey","Mouse","mickey@uwo.ca");
      	
    contacts.remove("John", "Doe");

    System.out.println(contacts.toString());
    System.out.println("I have " + contacts.getNumFriends() + " friends in my list.");
    
    boolean check = contacts.remove("Mickey","Mouse");
    if(check==true) {
    	System.out.println("Mickey Mouse was removed successfully.");
    }
    else {
    	System.out.println("Mickey Mouse was not found.");
    }
    
    boolean check2 = contacts.remove("John","Doe");
    if(check2==true) {
    	System.out.println("John Doe was removed successfully.");
    }
    else {
    	System.out.println("John Doe was not found.");
    }
    
    System.out.println("I have " + contacts.getNumFriends() + " friends in my list.");
  }
}
