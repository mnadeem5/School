package ca.uwo.csd.cs2212.zalbiraw;

import javax.swing.SwingUtilities;

public class Customer {
	  
    private String firstName;
    private String lastName;
    private String email;
    private double balanceOwing;
 
    public Customer(String firstName, String lastName, String email, double balanceOwing) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.balanceOwing = balanceOwing;
    }
    
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public double getBalanceOwing() {
        return balanceOwing;
    }
 
    public void setBalanceOwing(double balanceOwing) {
        this.balanceOwing = balanceOwing;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName)
          .append(" ")
          .append(lastName)
          .append(" (")
          .append(email)
          .append("): ")
          .append("$")
          .append(balanceOwing);
        
        return sb.toString();
    }
}