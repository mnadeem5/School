/**
 * Class that represents a social network as a list of persons (friends or contacts)
 * @author CS1027
 */
public class SocialNetwork {
	
	private final int DEFAULT_MAX_FRIENDS = 10;	// default size of array
	
	/* Attribute declarations */
	private Person[] friendList;		// array of persons (list of friends)
	private int numFriends;				// current number of persons in list
	
	/**
	 * Constructor creates person array of default size
	 */
	public SocialNetwork (){
		friendList = new Person[DEFAULT_MAX_FRIENDS];
		numFriends = 0;
	}
	
	/**
	 * Constructor creates person array of specified size
	 * @param max maximum size of array
	 */
	public SocialNetwork(int max){
		friendList = new Person[max];
		numFriends = 0;
	}

	/**
	 * add method adds a person to the list
	 * @param firstName first name
	 * @param lastName last name
	 * @param email email address
	 */
	public void add(String firstName, String lastName, String email){
		// create a new Person objetc
		Person friend = new Person(firstName, lastName, email);
				
		// add it to the array of friends
		// if array is not big enough, double its capacity automatically
		if (numFriends == friendList.length)
			expandCapacity();
		
		// add reference to friend at first free spot in array
		friendList[numFriends] = friend;
		numFriends++;		
	}

	/**
	 * remove method removes a specified friend from the list
	 * @param firstName first name of person to be removed
	 * @param lastName last name of person to be removed
	 * @return true if friend was removed successfully, false otherwise
	 */
	public boolean remove(String firstName, String lastName){
		final int NOT_FOUND = -1;
		int search = NOT_FOUND;
		Person target = new Person(firstName, lastName, "");	

		// if list is empty, can't remove
		if (numFriends == 0){
			return false;
		}
		// search the list for the specified friend
		for (int i = 0; i < numFriends && search == NOT_FOUND; i ++)
			if (friendList[i].equals(target))
				search = i;
		
		// if not found, can't remove 
		if (search == NOT_FOUND)
			return false;
		
		// target person found, remove by replacing with last one in list
		friendList[search] = friendList[numFriends - 1];
		friendList[numFriends - 1] = null;
		numFriends --;
		
		return true;			
	}
		
	/**
	 * toString method returns a string representation of all persons in the list
	 * @return first name and last name, email address of each person 
	 */
	public String toString(){
		String s = "";
		for (int i = 0; i < numFriends; i++){
			s = s + friendList[i].toString()+ "\n";
		}
		return s;
	}
	
	/**
	 * expandCapacity method is a helper method
	 * that creates a new array to store friends with twice the capacity
	 * of the existing one
	 */
	private void expandCapacity(){
		Person[] largerList = new Person[friendList.length * 2];
		for (int i = 0; i < friendList.length; i++)
			largerList[i] = friendList[i];
		
		friendList = largerList;
	}
	
	/**
	 * @return Method returns the integer value from the number of friends in the Person array
	 */

	public int getNumFriends() {
		return numFriends;
	}
	
	/**
	 * Method sets the number of friends in SocialNetwork to 0; clears the array
	 */
	
	public void clearFriends() {
		numFriends = 0;
	}
	
	/**
	 * test harness
	 */

	public static void main (String[] args) {
		SocialNetwork socialNetwork = new SocialNetwork();
		socialNetwork.add("John", "Doe", "jdoe1@uwo.ca");
		socialNetwork.add("Jack", "Doe", "jdoe2@uwo.ca");
		socialNetwork.add("Jerry", "Doe", "jdoe3@uwo.ca");
		
		System.out.println(socialNetwork);
		System.out.println(socialNetwork.getNumFriends());
		
		socialNetwork.clearFriends();
		System.out.println(socialNetwork.getNumFriends());
	}
}

