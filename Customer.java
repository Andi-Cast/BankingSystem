//Customer.java
import java.util.*;
import java.io.*;


public class Customer extends User implements Serializable{
	CheckingAccount checkingAccount; 
	SavingAccount savingAccount;
	

	Customer() {
		name = "Unknown";
		accountNumber = 11111;
		PIN = 22222;	
	}//end Customer()

	Customer(String name, int accountNumber, int PIN, CheckingAccount checkingAccount, SavingAccount savingAccount) {
		this.name = name;
		this.accountNumber = accountNumber;
		this.PIN = PIN;
		//this allows for changes to happen within this class and not the CheckingAccount/SavingAccount class
		this.checkingAccount = new CheckingAccount(checkingAccount.getBalance());
		this.savingAccount = new SavingAccount(savingAccount.getBalance(), savingAccount.getInterest());
	}//end Customer(name, accountNumber, PIN)

	@Override 
	public String toString() {
		return name + " | " + accountNumber + " | " + PIN + " | " + this.checkingAccount.getBalance() + " | " + this.savingAccount.getBalance() + " | " + this.savingAccount.getInterest();
	}//end getInfo

	public void setCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = new CheckingAccount(checkingAccount.getBalance());
	}//end setCheckingAccount

	public CheckingAccount getCheckingAccount() {
		return new CheckingAccount(this.checkingAccount.getBalance());
	}//end getCheckingAccount

	public void setSavingAccount(SavingAccount savingAccount) {
		this.savingAccount = new SavingAccount(savingAccount.getBalance(), savingAccount.getInterest());
	}//end setSavingAccount

	public SavingAccount getSavingAccount() {
		return new SavingAccount(this.savingAccount.getBalance(), this.savingAccount.getInterest());
	}//end getSavingAccount

	public void exit() {

	}//end exit 

	public void customerMenu() {
		Scanner input = new Scanner(System.in);
		boolean Active = true;

	while(Active == true) {	
		//loads array list to modify
		File file = new File("UserInfo.ser");
                ArrayList<Customer> customerList = new ArrayList<>();
                ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
                 //loads array list
                try {
                        FileInputStream fis = new FileInputStream(file);
                        ois = new ObjectInputStream(fis);
                        customerList = (ArrayList<Customer>)ois.readObject();
                        ois.close();
                        fis.close();
                }
                catch (IOException e){
                        System.out.println(e);
                }
                catch (ClassNotFoundException ex) {
                        System.out.println(ex);
                }

		//menu options
		System.out.println("\nWelcome, " + name + " What would you like to do?\n1:Check Account Balances\n2:Deposit \n3.Withdraw\n4.Exit");
		
		int selection = 0;
		int depositOption = -1;
		int depositAmount = -1;	
		int withdrawOption = -1;
		int withdrawAmount = -1;
		boolean validInput = false; 
			
	
	
		//validates input
		while(validInput == false) {
			if (input.hasNextInt()){
				selection = input.nextInt();
				if (selection < 1 || selection > 4) {
					System.out.print("Invalid input. Please select one of the options: ");
				
				}
				else {
					validInput = true;
				}
			}
			else {
				System.out.print("Invalid input. Please select one of the options: ");
				input.next();
			}
		}//end while
		
		

		
		if(selection == 1) {
			System.out.println("Checkin Account Balance: $" + this.checkingAccount.getBalance() + "\nSaving Account Balance: $" + this.savingAccount.getBalance());
		}//end of selection 1

		else if (selection == 2) {
			System.out.println("What account would you like to deposit to? \n1.Checking Acount \n2.Saving Account?");
		       	validInput = false;

			while(validInput == false) {
                        	if (input.hasNextInt()){
                                	depositOption = input.nextInt();
                                	if (depositOption < 1 || depositOption > 2) {
                                        	System.out.print("Invalid input. Please select one of the options: ");

                                	}
                                	else {
                                        	validInput = true;
                                	}
                       		}
                        	else {
                                	System.out.print("Invalid input. Please select one of the options: ");
                                	input.next();
                        	}
                	}//end deposit account option validator
	
			if (depositOption == 1) {
				System.out.print("How much would you like to deposit to your checking account?: $");
				validInput = false;

				while(validInput == false) {
		                        if (input.hasNextInt()){
                	                	depositAmount = input.nextInt();
                        	        	if (depositAmount < 0) {
                                	        	System.out.print("Invalid input. Try again: ");
                                		}	
                                		else {
                                        	validInput = true;
                                		}
                        		}
                        		else {
                                		System.out.print("Invalid input. Try again: ");
                                		input.next();
                       			}
				}//end deposit ammount validator

				this.checkingAccount.deposit(depositAmount);
				depositAmount = 0; 
				System.out.println("New checking account balance: $" + this.checkingAccount.getBalance());
			}

			else {
				System.out.print("How much would you like to deposit to your saving account?: $");
                                validInput = false;

                                while(validInput == false) {
                                        if (input.hasNextInt()){
                                                depositAmount = input.nextInt();
                                                if (depositAmount < 0) {
                                                        System.out.print("Invalid input. Try again: ");
                                                }
                                                else {
                                                validInput = true;
                                                }
                                        }
                                        else {
                                                System.out.print("Invalid input. Try again: ");
                                                input.next();
                                        }
                                }//end deposit ammount validator

                                this.savingAccount.deposit(depositAmount);
                                depositAmount = 0;
                                System.out.println("New saving account balance: $" + this.savingAccount.getBalance());

			}
			
		}//end if else 2

		else if (selection == 3) {
			System.out.println("Withdraw");
			
			System.out.println("What account would you like to withdraw from? \n1.Checking Acount \n2.Saving Account?");
                        validInput = false;

                        while(validInput == false) {
                                if (input.hasNextInt()){
                                        withdrawOption = input.nextInt();
                                        if (withdrawOption < 1 || withdrawOption > 2) {
                                                System.out.print("Invalid input. Please select one of the options: ");

                                        }
                                        else {
                                                validInput = true;
                                        }
                                }
                                else {
                                        System.out.print("Invalid input. Please select one of the options: ");
                                        input.next();
                                }
                        }//end deposit account option validator

                        if (withdrawOption == 1) {
                                System.out.print("How much would you like to withdraw to your checking account?: $");
                                validInput = false;

                                while(validInput == false) {
                                        if (input.hasNextInt()){
                                                withdrawAmount = input.nextInt();
                                                if (withdrawAmount < 0 || withdrawAmount > this.checkingAccount.getBalance()) {
                                                        System.out.print("Invalid input. Input an amount greater than $0 and less than you balance ($" + this.checkingAccount.getBalance() +") Try again: $");
                                                }
                                                else {
                                                validInput = true;
                                                }
                                        }
                                        else {
                                                System.out.print("Invalid input. Try again: ");
                                                input.next();
                                        }
                                }//end deposit ammount validator

                                this.checkingAccount.withdraw(withdrawAmount);
                                withdrawAmount = 0;
                                System.out.println("New checking account balance: $" + this.checkingAccount.getBalance());
                        }

                        else {
                                System.out.print("How much would you like to withdraw to your saving account?: $");
                                validInput = false;

                                while(validInput == false) {
                                        if (input.hasNextInt()){
                                                withdrawAmount = input.nextInt();
                                                if (withdrawAmount < 0 || withdrawAmount > this.savingAccount.getBalance()) {
                                                        System.out.print("Invalid input. Input an amount greater than $0 and less than your balance ($" + this.savingAccount.getBalance() +") Try again: $");
                                                }
                                                else {
                                                validInput = true;
                                                }
                                        }
                                        else {
                                                System.out.print("Invalid input. Try again: ");
                                                input.next();
                                        }
                                }//end deposit ammount validator

				this.savingAccount.withdraw(withdrawAmount);
                                withdrawAmount = 0;
                                System.out.println("New saving account balance: $" + this.savingAccount.getBalance());
			}

		}//end if else 3

		else {
			//find customer and updates data
			boolean changes = false;
			ListIterator<Customer> c = customerList.listIterator();
				
				
	
			while (c.hasNext()) {
				Customer cc = c.next();
				int checkPIN = cc.getPIN();

				if (checkPIN  == this.getPIN()) {
					c.set(new Customer(this.getName(),this.getAccountNumber(),this.getPIN(), this.getCheckingAccount(), this.getSavingAccount()));
					changes = true;
				}
			}
			if (!changes) {
				System.out.println("Error with saving recent account activity");
			}


			//saves changes
			try {
				FileOutputStream fos = new FileOutputStream(file);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(customerList);
				oos.close();
				fos.close();
			}
			catch (IOException e) {
				System.out.println(e);
			}

			System.out.println("\nThank you for using RETH. Please come back again!");
			Active = false;

		}//end of selection 4
	}//while active

	}//end customerMenu


}
