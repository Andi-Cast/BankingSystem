//Account.java
import java.io.*;

public class Account implements Serializable{
	int balance = 10;

	public Integer getBalance(){
		return this.balance;
	}//end getBalance

	public void deposit(int amount) {
		balance += amount;
	}//end deposit

	public void withdraw(int amount) {
		balance  -= amount; 
	}//end withdraw
}
