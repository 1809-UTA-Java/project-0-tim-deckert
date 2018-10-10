package com.revature.app;

import com.revature.resources.AccountsDao;
import com.revature.resources.UserDao;
import com.revature.resources.UserInput;

//import java.util.ArrayList;
//import java.util.HashMap;

final public class Admin extends Employee {
	// private static ArrayList <Account> masterAccountList = new
	// ArrayList<Account>();
	private AccountsDao accd;

	public Admin(String username, String password) {
		super(username, password);
		accd = new AccountsDao();
	}

	@Override
	public void pullUsers() {
		UserDao userd = new UserDao();
		users = userd.getUsersAdmin(this.employeeID);
	}

	@Override
	public void viewAccounts() {
		this.accounts = accd.getAccountsAdmin(this.username);
		for (Account a : accounts) {
			a.displayAccount();
		}
	}

	public void deposit() {
		while (true) {
			try {
				Integer accIndex = Integer.parseInt(UserInput.getData("account number to deposit to: "));
				if (accIndex < 0) {
					UserInput.getData("No negative numbers, please.");
					continue;
				}

				Double depositAmount = Double.parseDouble(UserInput.getData("How much would you like to deposit: "));
				if (depositAmount < 0) {
					String answer = UserInput
							.getData("No negative numbers, please. Would you like to withdraw instead? (yes/no)");
					while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
						answer = UserInput.getData("Only answer yes or no, please: ");
					}
					if (answer.equalsIgnoreCase("yes")) {
						this.withdraw();
						return;
					}
					continue;
				}

				accd.getAccount(accIndex).alterFunds(depositAmount);
				break;

			} catch (NumberFormatException ex) {
				System.out.println("Sorry, that does not look like a number. Please try again.");
			}
		}
	}

	public void withdraw() {
		while (true) {
			try {
				Integer accIndex = Integer.parseInt(UserInput.getData("account number to withdraw from: "));

				if (accIndex < 0) {
					UserInput.getData("No negative numbers, please.");
					continue;
				}

				Double withdrawAmount = Double.parseDouble(UserInput.getData("How much would you like to withdraw: "));
				if (withdrawAmount < 0) {
					String answer = UserInput
							.getData("No negative numbers, please. Would you like to deposit instead? (yes/no)");
					while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
						answer = UserInput.getData("Only answer yes or no, please: ");
					}
					if (answer.equalsIgnoreCase("yes")) {
						this.deposit();
						return;
					}
				}

				accd.getAccount(accIndex).alterFunds(-withdrawAmount);
				break;

			} catch (NumberFormatException ex) {
				System.out.println("Sorry, that does not look like a number. Please try again.");
			}
		}

	}

	public void transfer() {
		while (true) {
			try {
				Integer accIndexFrom = Integer.parseInt(UserInput.getData("account number to transfer from: "));
				Integer accIndexTo = Integer.parseInt(UserInput.getData("account number transfer to: "));

				if (accIndexFrom < 0 || accIndexTo < 0) {
					UserInput.getData("No negative numbers, please.");
					continue;
				}

				Double amount = Double.parseDouble(UserInput.getData("How much would you like to transfer: "));
				if (amount < 0) {
					String answer = UserInput
							.getData("You entered a negative number. Would you like to swap to and from? (yes/no)");
					while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
						answer = UserInput.getData("Only answer yes or no, please: ");
					}
					if (answer.equalsIgnoreCase("yes")) {
						accIndexFrom += accIndexTo;
						accIndexTo = accIndexFrom - accIndexTo;
						accIndexFrom -= accIndexTo;
					}
				}

				accd.getAccount(accIndexFrom).alterFunds(-amount);
				accd.getAccount(accIndexTo).alterFunds(amount);
				break;

			} catch (NumberFormatException ex) {
				System.out.println("Sorry, that does not look like a number. Please try again.");
			}
		}
	}

	public void delete() {
		while (true) {
			try {
				Integer accIndex = Integer.parseInt(UserInput.getData("account number to deposit to: "));
				if (accIndex < 0) {
					UserInput.getData("No negative numbers, please.");
					continue;
				}

				accd.deleteAccount(accIndex);
				break;

			} catch (NumberFormatException ex) {
				System.out.println("Sorry, that does not look like a number. Please try again.");
			}
		}
	}
}