package ca.uwo.csd.cs2212.zalbiraw;

public class Customer {

	private String firstName;
	private String lastName;
	private String gender;
	private String planName;
	private double balanceOwing;

	public Customer(String firstName, String lastName, String gender,
			String planName, double balanceOwing) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.planName = planName;
		this.balanceOwing = balanceOwing;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getGender() {
		return this.gender;
	}

	public String getPlanName() {
		return this.planName;
	}

	public double getBalanceOwing() {
		return this.balanceOwing;
	}

}