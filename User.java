//User.java
import java.io.*;


public class User implements Serializable{
	String name;
	int accountNumber;
	int PIN;


	public String getName() {
		return name;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public Integer getPIN() {
		return PIN;
	}

}
