package ca.uwo.csd.cs2212.zalbiraw;

import java.io.Serializable;
import java.lang.StringBuilder;

public class Address implements Serializable
{
	private String street;
	private String city;
	private String country;

	public Address(String street, String city, String country)
	{
		this.street = street;
		this.city = city;
		this.country = country;
	}

	public String toString() 
	{
		StringBuilder msg = new StringBuilder();

		msg.append("Street    : ");
		msg.append(this.street);
		msg.append("\n");

		msg.append("City      : ");
		msg.append(this.city);
		msg.append("\n");

		msg.append("Country   : ");
		msg.append(this.country);
		msg.append("\n");

		return msg.toString();
	}

}