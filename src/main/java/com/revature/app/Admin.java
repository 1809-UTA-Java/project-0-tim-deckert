package com.revature.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.resources.AccountsDao;
import com.revature.resources.EmployeeDao;
import com.revature.resources.JDBCUtil;
import com.revature.resources.UserDao;
import com.revature.resources.UserFactory;
import com.revature.resources.UserInput;

//import java.util.ArrayList;
//import java.util.HashMap;

final public class Admin extends Employee {
	// private static ArrayList <Account> masterAccountList = new
	private ArrayList<Employee> employees;
	private AccountsDao accd;

	public Admin(String username, String password) {
		super(username, password);
		accd = new AccountsDao();
		
		PreparedStatement ps = null;
		
		try (Connection conn = JDBCUtil.getConnection()) {
        	String sql = "INSERT INTO EMPLOYEE (UNAME, PERMISSION) VALUES (?, 'A')";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, username);
        	//ps.setString(2, password);
        	ps.executeQuery();
        	ps.close();	
        	
        } catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
        }
		
	}

	@Override
	public void pullUsers() {
		UserDao userd = new UserDao();
		users = userd.getUsersAdmin(this.employeeID);
	}

	public void pullEmployees() {
		EmployeeDao empd = new EmployeeDao();
		employees = empd.getEmployees();
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

	public void hireEmployee() {
		UserFactory.createEmployee();
	}
	public void hireAdmin() {
		UserFactory.createAdmin();
	}
	public void fireEmployee() {
		UserInput.getData("Enter the ID number of the employee you want to fire");
		PreparedStatement ps = null;
		
		try (Connection conn = JDBCUtil.getConnection()) {
//			String sql = "DELETE FROM USERS WHERE UNAME = "
//					+ "(SELECT USER.UNAME FROM USERS INNER JOIN EMPLOYEE ON USERS.UNAME = EMPLOYEE.UNAME WHERE EMPLOYEE.E_ID = ?;";
        	String sql = "DELETE FROM EMPLOYEE WHERE E_ID = (?)";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, username);
        	//ps.setString(2, username);
        	//ps.setString(2, password);
        	ps.executeQuery();
        	ps.close();	
        	
        } catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
        }
		
	}

	public void viewEmployees() {
		for (Employee e : employees) {
			e.displayEmployee();
		}
	}
	
}