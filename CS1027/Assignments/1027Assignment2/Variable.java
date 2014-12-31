/**
 * This class represents a rectangular shape.
 * @author Zaid Albirawi (zalbiraw)
 */
public class Variable 
{
	/**
	 * name of the variable
	 */
	private String vName;
	
	/**
	 * variable value
	 */
	private int vValue;
	
	/**
	 * Constructor creates a variable
	 * @param Variable name.
	 * @param Variable value.
	 */
	public Variable (String name, int value)
	{
		this.vName=name;
		this.vValue=value;
	}
	
	/**
	 * Accessor method to get the name of the variable.
	 * @return variable name.
	 */
	public String getName()
	{
		return this.vName;
	}
	
	/**
	 * Accessor method to get the value of the variable.
	 * @return variable value.
	 */
	public Integer getValue()
	{
		return this.vValue;
	}
	
	/**
	 * Setter method to set the value of the variable.
	 */
	public void setValue (int value)
	{
		this.vValue=value;
	}
}
