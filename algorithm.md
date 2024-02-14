Goals:
-create a banking system 
	-admin
		-display a admin menu that has the ability to: 
			-addiing new account
			-displaying all accounts
			-apply interest to all saving accounts
			-exit
	-customers
		-display customer menu that has the ability to:
			-check balances
			-deposit
			-withdraw
			-exit

Input:
In the main menu there will be two inputs: account number and pin
In the Customer menu: integer input for selection from menu and integer for amount to either deposit or withdraw

Output:
The outputs include the main menu, customer menu, admin menu, customer list, and attributes of classes

Steps:
I will create a parent class for the customer and admin called User since they share data member - name, pin , and account number.
They will also allow the inherited classes to use its methods like setters and getters.

The two classes from the user will be Customer (one for each account) and Admin (only one for grader)
The Customer class will have two aggregated data memmbers- saving acount and checking account. This class will have setters and getters for all data members.

There will also be a parent class for saving and checking account called Account since they share similiar data members-balance. Also share methods-getters for data members. 

I will be storing all the instances of Customers an ArrayList which will be saved into a file to refer to everytime the programs is run. 

In the main function i will first check if the input  account number and pin match any account. If it matches admin then the admin menu will pop up. If a customer is matched then a customer menu pops up. A message will pop up if there isnt any matches. 
