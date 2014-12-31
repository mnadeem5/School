/**
 * DictEntry is java class that contains the information about the Dictionary entries.
 * 
 * @author Zaid Albirawi
 * Student #:
 * Mail: zalbiraw@uwo.ca
 * Course #: CS2210
 * Assignment #2
 * 
 * @version 1.0 15/10/2013
 */

public class DictEntry 
{
	private String key;
	private int code;
	
	public DictEntry (String key, int code)
	{
		this.key=key;
		this.code=code;
	}

	public String getKey(){return key;}
	
	public int getCode(){return code;}
}
