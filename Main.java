//Main.java
import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception{	
		boolean login = false;
		Scanner input = new Scanner(System.in);
		File file = new File("UserInfo.ser");
		ArrayList<Customer> customerList = new ArrayList<>();
		ObjectInputStream ois = null;
			
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
		

		//login info
		while (login == false) {
			System.out.println("-------------------------------------------");
			System.out.print("Enter Account Number: ");
			int accountNumber = input.nextInt();

			System.out.print("\nEnter PIN: ");
			int PIN = input.nextInt(); 

			if (accountNumber == 00000 && PIN == 12345) {
				Admin a = new Admin();
				a.adminMenu();
				login = true;
			}
			else {
				//checks for a match 
				boolean match = false;
				ListIterator<Customer> m = customerList.listIterator();	

				while (m.hasNext()) {
					Customer cm = m.next();
					if (cm.getPIN() == PIN && cm.getAccountNumber() == accountNumber) {
						System.out.println("Login was Successful");
						cm.customerMenu();
						login = true;
						match = true;
					}
				}

				if (!match) {
					System.out.println("No match were found in system");
					System.out.println("Try again");
				}
			}
		}//end while login


	}//end public static void 

}//end main
