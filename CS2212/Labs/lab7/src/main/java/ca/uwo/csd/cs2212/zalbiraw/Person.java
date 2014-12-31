package ca.uwo.csd.cs2212.zalbiraw;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

public class Person implements Serializable
{
	private String name;
	private String gender;
	private Address address;
	private List<String> phoneNumbers;
	private static final long serialVersionID = 2L;
	

	public Person() {this.phoneNumbers = new ArrayList<String>();}

	public Person(String name, Address address) 
	{
		this();
		this.name = name;
		this.address = address;
	}

	public void addPhoneNumber(String number) {this.phoneNumbers.add(number);}

	@Override
	public String toString()
	{
		StringBuilder msg = new StringBuilder();

		msg.append("Name      : ");
		msg.append(this.name);
		msg.append("\n");

		if (this.address != null)msg.append(this.address.toString());
		else msg.append("Address: null");

		for (String number : this.phoneNumbers)
		{
			msg.append("Telephone : ");
			msg.append(number);
			msg.append("\n");
		}
		return msg.toString();
	}
}