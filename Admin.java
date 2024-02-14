//Admin.java
import java.util.*;
import java.io.*; 
import java.lang.Math;


public class Admin extends User implements Serializable {
	
	Admin() {
		name = "Grader";
		accountNumber = 00000;
		PIN = 12345;
	}//end Admin

	Admin(String name, int accountNumber, int PIN) {
		this.name = name;
		this.accountNumber = accountNumber;
		this.PIN = PIN;
	}//end Admin (name, account number, pin)


	public void adminMenu() {
		Scanner input = new Scanner(System.in); 
		Scanner inputString = new Scanner(System.in);	
		boolean active = true;

		File file = new File("UserInfo.ser");
		ArrayList<Customer> customerList = new ArrayList<>();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;


		//repeat menu until exit is selected
		while (active == true) {
			int selection = 0;
			boolean validInput = false;

			System.out.println("Welcome, " + name + " would you like to do?\n1.Add An Account\n2.List All Accounts\n3.Delete Account\n4.Apply Interest to All Savings Accounts\n5.Exit");
			System.out.print("Enter an option: ");
			//validates input
			while(validInput == false) {
	                        if (input.hasNextInt()) {
        	                        selection = input.nextInt();
                	                if (selection < 1 || selection > 5) {
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
                	}//end input validator

		
			if (selection == 1) {
				//loads updated array List
				try {
					FileInputStream fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					customerList = (ArrayList<Customer>)ois.readObject();
					ois.close();
					fis.close();
					for (Customer customer : customerList) {
						System.out.println(customer.toString());
					}
				}
				catch (IOException e){
					System.out.println(e);
				}
				catch (ClassNotFoundException ex) {
					System.out.println(ex);
				}

				//new Customer info
				System.out.println("\nNew Customer Information Form");
				System.out.println("------------------------------------------------------------------------------------------------");

				System.out.print("Enter name: ");
				String name = inputString.nextLine();
				
				System.out.print("\nEnter Account Number(5 digits): ");
				int accountNumber = input.nextInt();

				System.out.print("\nEnter PIN (5 digits): ");
				int PIN = input.nextInt();

				System.out.print("\nEnter Checking Account Balance: ");
				int checkingBalance = input.nextInt();

				System.out.print("\nEnter Saving Account Balance: ");
				int savingBalance = input.nextInt();

				System.out.print("\nEnter Saving Account Interest Rate: ");
				int interest = input.nextInt(); 

				//creates aggregation classes to put into Customer class
				CheckingAccount ca = new CheckingAccount(checkingBalance);
				SavingAccount sa = new SavingAccount(savingBalance, interest);

				//add new Customer to array list
				customerList.add(new Customer(name, accountNumber, PIN, ca, sa));
				
				//saves the updated array list with new Customer into file
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

				System.out.println("\nCustomer was successfully added!");
				System.out.println("------------------------------------------------------------------------------------------------");

			}//end selection 1
							
			else if (selection == 2 ) {	
				//loads array list from saved file
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

				//iterator for list
				Iterator<Customer> i = customerList.iterator();

				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("Name|Account Number|PIN|Checking Account Balance|Saving Account Balance|Saving Account Interest");

				//prints each object
				while(i.hasNext()) {
					Customer c = i.next();
					System.out.println(c.toString());
				}

				System.out.println("------------------------------------------------------------------------------------------------");
			} //end selection 2

			else if (selection == 3) {
				boolean delete = false;
				
				//loads array list from file 
				//ensures array list is updated if any chances were made before exiting
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

				//delete account
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("Enter PIN of account you wish to delete: ");
				int deletePIN = input.nextInt();
				Iterator<Customer> d = customerList.iterator();

				while(d.hasNext()) {
					Customer dc = d.next();
					//if match is found
					if (dc.getPIN() == deletePIN) {
						if (dc.checkingAccount.getBalance() == 0 && dc.savingAccount.getBalance() == 0) {
							System.out.println(dc.getName() + "'s account was successfully deleted.");
							System.out.println("\n------------------------------------------------------------------------------------------------\n");
							d.remove();
							delete = true;
						}
						else {
							System.out.println("\nUnable to delete account. One or both account still have a balance greater than $0."); 
							System.out.println("\n------------------------------------------------------------------------------------------------\n");
							delete = true;
						} 
					}
				}
				//if no match is found
				if(!delete) {
					System.out.println("\nNo matches for account to be deleted");
					System.out.println("\n------------------------------------------------------------------------------------------------\n");
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

			}//end selection 3

			else if (selection == 4) {
				//loads most updated array list
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

				//update interest rate
				boolean update = false;

				System.out.println("Applying interest to all saving accounts........");
				ListIterator<Customer> u = customerList.listIterator();

				while (u.hasNext()) {
					Customer uc = u.next();
					//p(1+rt)
					int newBalance=  uc.savingAccount.getBalance()*(1 + (12*uc.savingAccount.getInterest()));

					SavingAccount us = new SavingAccount(newBalance, uc.savingAccount.getInterest());
					u.set(new Customer(uc.getName(), uc.getAccountNumber(), uc.getPIN(), uc.getCheckingAccount(), us));
					update = true;
				}	

				if (!update) {
					System.out.println("\nInterest was unable to be updated");
					System.out.println("------------------------------------------------------------------------------------------------");
				}
				else {
					System.out.println("New interest rate was successfully updated.");
					System.out.println("------------------------------------------------------------------------------------------------");
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

			}//end selcetion 4

			else {
				System.out.println("RETH appreciates you. Have great rest of your day " + name + "\n");
				active = false;
			}//end selection 5	
		}//end while active


	}//end Admin Menu
}







