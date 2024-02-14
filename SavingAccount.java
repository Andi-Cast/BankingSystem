//SavingAccount.java
import java.io.*;

public class SavingAccount extends Account implements Serializable {
	int interest;

	SavingAccount() {
		balance = 0;
		interest = 0;
	}//end SavingAccount

	SavingAccount(int balance, int interest) {
		this.balance = balance;
		this.interest = interest;
	}//end SavingAccount(amount, interest)

	public void setInterest(int interest) {
		this.interest = interest;
	}//end setInterest

	public Integer getInterest(){
		return interest;
	}
}
