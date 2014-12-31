/**
 * This class represents a rectangular shape.
 * @author Zaid Albirawi (zalbiraw)
 */
public class VariableList 
{
	/**
	 * the max value for the length of the listVariables array
	 */
	private final int MAX_VARIABLES=30;
	
	/**
	 * an array to store a list of Variables 
	 */
	public Variable listVariables [];
	
	/**
	 * integer to keep track of the number of variables.
	 */
	private int vCount;
	
	/**
	 * Constructor creates an array to hold the variables list and their values
	 */
	public VariableList ()
	{
		this.listVariables = new Variable [MAX_VARIABLES];
		this.vCount=0;
	}
	
	/**
	 * Constructor creates an array to hold the variables list and their values
	 * @param sets the max length of the array
	 */
	public VariableList (int aryMax)
	{
		this.listVariables = new Variable [aryMax];
		this.vCount=0;
	}
	
	/**
	 * add method to add new variable to the variable list and sets its value to ?
	 * @param variable name.
	 */
	public void add(String vName)
	{
		Variable var=new Variable (vName, -1);
		this.listVariables[this.vCount]=var;
		this.vCount++;
	}
	
	/**
	 * add method to add a new variable to the variable list
	 * @param vraible name.
	 * @param variable value.
	 */
	public void add(String vName, int vValue)
	{
		Variable var=new Variable (vName, vValue);
		this.listVariables[this.vCount]=var;
		this.vCount++;
	}
	
	/**
	 * find method to find the variable and return its number in the array, if the variable
	 * is not found then it creates one.
	 * @param name of the variable that is to be found
	 * @return the number of the variable in the array
	 */
	public Integer find(String findName)
	{
		int index=0;
		while (this.listVariables[index]!=null)
		{
			if (this.listVariables[index].getName().equals(findName))
			{
				return index;
			}
			else index++;
		}
		/**
		 * adds the variable if it doens't exist
		 */
		add (findName);
		return this.vCount-1;
	}
	
	/**
	 * check method to check if the character is an existing variable.
	 * @param name of the variable that is to be checked.
	 * @return true if the variable does exist.
	 */
	public boolean check (String findName)
	{
		int index=0;
		while (this.listVariables[index]!=null)
		{
			if (this.listVariables[index].getName().equals(findName))
			{
				return true;
			}
			else index++;
		}
		/**
		 * returns false if the variable doesn't exist
		 */
		return false;
	}
}
