//CheckingAccount.java
import java.io.*;

public class CheckingAccount extends Account implements Serializable{
	CheckingAccount() {
		balance = 0;
	}//end CheckingAccount

	CheckingAccount(int balance) {
		this.balance = balance;
	}//end CheckinAccount(amount)
}
