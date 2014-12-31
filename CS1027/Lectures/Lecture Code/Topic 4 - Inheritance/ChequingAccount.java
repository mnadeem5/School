public class ChequingAccount extends BankAccount {

    public int transactionCount ;

    public ChequingAccount(int accountNumber, int balance) {

        super(accountNumber,balance) ;
        transactionCount = 0 ;
    	System.out.println("constructor from class ChequingAccount\n");
    }

    public void deposit(int amount) {

        super.deposit(amount) ;
        transactionCount++ ;
        System.out.println("deposit from class ChequingAccount\n") ;
    }

    public void withdraw(int amount) {

        super.withdraw(amount) ;
        transactionCount++ ;
	System.out.println("withdraw from class ChequingAccount\n") ;
    }
}
