public class BankAccount {
    
    private int accountNumber ;
    private int balance ;

    public BankAccount(int accNumber,int bal) {
        
        accountNumber = accNumber ;
        balance = bal ;
        System.out.println("constructor from class Bankaccount\n") ;
    }

    public void deposit(int amount) {

        balance += amount ;
        System.out.println("Deposit from class BankAccount\n") ;
    }

    public void withdraw(int amount) {

        balance -= amount ;
	System.out.println("Withdraw from class BankAccount\n") ;
    }

    public int getBalance() {

        return balance ;
    }
}
