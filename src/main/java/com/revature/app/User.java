package com.revature.app;

import java.util.Queue;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.resources.*;


public class User implements Serializable {
	protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected Character middleInitial;
    protected String address;
    protected String city;
    protected String state;
    protected Integer zip;
    
    protected ArrayList <Account> accounts;
    //protected static Queue <Application> applications;

    User () {}
    public User (String username, String password) {
        this.username = username;
        this.password = password;
        PreparedStatement ps = null;
        
        try (Connection conn = JDBCUtil.getConnection()) {
        	String sql = "INSERT INTO USERS (UNAME, PWORD) VALUES (?, ?)";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, username);
        	ps.setString(2, password);
        	ps.executeQuery();
        	ps.close();
        	
        } catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
        }
        
    }
    
	public User(String username, String firstName, String lastName, Character middleInitial,
			String address, String city, String state, Integer zip) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
    
    public void apply () {
    	UserInput.apply(username);
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    public void viewAccounts() {
    	AccountsDao accd = new AccountsDao();
    	this.accounts = accd.getAccountsUser(this.username);
    	int i = 1;
    	for (Account a: accounts) {
    		System.out.print(i++ +") ");
    		a.displayAccount();
    	}
    }
    
    public void deposit() {
    	this.viewAccounts();
    	while (true) {
	    	try {
	    		Integer accIndex = Integer.parseInt(UserInput.getData("Which account are you depositing to: "))-1;
	    		if (accIndex > this.accounts.size()-1 || accIndex < 0) {
	    			System.out.println("Only numbers between 1-"+this.accounts.size()+" are accepted.");
	    			continue;
	    		}
	    		
	    		Double depositAmount = Double.parseDouble(UserInput.getData("How much would you like to deposit: "));
	    		if (depositAmount < 0) {
	    			String answer = UserInput.getData("No negative numbers, please. Would you like to withdraw instead? (yes/no)");
	    			while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
	            		answer = UserInput.getData("Only answer yes or no, please: ");
	            	}
	    			if (answer.equalsIgnoreCase("yes")) {
	    				this.withdraw();
	    				return;
	    			}
	    			continue;
	    		}
	    		
	    		accounts.get(accIndex).alterFunds(depositAmount);
	    		break;
	    		
	    	} catch (NumberFormatException ex) {
	    		System.out.println("Sorry, that does not look like a number. Please try again.");
	    	}
    	}
    }
    
    public void withdraw() {
    	this.viewAccounts();
    	while (true) {
	    	try {
	    		Integer accIndex = Integer.parseInt(UserInput.getData("Which account are you withdrawing from: "))-1;
	    		
	    		if (accIndex > this.accounts.size()-1 || accIndex < 0) {
	    			System.out.println("Only numbers between 1-"+this.accounts.size()+" are accepted.");
	    			continue;
	    		}
	    		
	    		Double withdrawAmount = Double.parseDouble(UserInput.getData("How much would you like to withdraw: "));
	    		if (withdrawAmount < 0) {
	    			String answer = UserInput.getData("No negative numbers, please. Would you like to deposit instead? (yes/no)");
	    			while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
	            		answer = UserInput.getData("Only answer yes or no, please: ");
	            	}
	    			if (answer.equalsIgnoreCase("yes")) {
	    				this.deposit();
	    				return;
	    			}
	    		}
	    		
	    		accounts.get(accIndex).alterFunds(-withdrawAmount);
	    		break;
	    		
	    	} catch (NumberFormatException ex) {
	    		System.out.println("Sorry, that does not look like a number. Please try again.");
	    	}
    	}
    	
    }

    public void transfer() {
    	this.viewAccounts();
    	while (true) {
	    	try {
	    		Integer accIndexFrom = Integer.parseInt(UserInput.getData("Which account would you like to transfer from: "))-1;
	    		Integer accIndexTo = Integer.parseInt(UserInput.getData("Which account would you like to transfer to: "))-1;
	    		
	    		if (accIndexFrom > this.accounts.size()-1 || accIndexFrom < 0
	    				|| accIndexTo > this.accounts.size()-1 || accIndexTo < 0) {
	    			System.out.println("Only numbers between 1-"+this.accounts.size()+" are accepted.");
	    			continue;
	    		}
	    		
	    		Double amount = Double.parseDouble(UserInput.getData("How much would you like to transfer: "));
	    		if (amount < 0) {
	    			String answer = UserInput.getData("You entered a negative number. Would you like to swap to and from? (yes/no)");
	    			while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
	            		answer = UserInput.getData("Only answer yes or no, please: ");
	            	}
	    			if (answer.equalsIgnoreCase("yes")) {
	    				accIndexFrom += accIndexTo;
	    				accIndexTo = accIndexFrom - accIndexTo;
	    				accIndexFrom -= accIndexTo;
	    			}
	    		}
	    		
	    		accounts.get(accIndexFrom).alterFunds(-amount);
	    		accounts.get(accIndexTo).alterFunds(amount);
	    		break;
	    		
	    	} catch (NumberFormatException ex) {
	    		System.out.println("Sorry, that does not look like a number. Please try again.");
	    	}
    	}
	
    }

    public void displayUser() {
    	System.out.println("User: "+username);
    	System.out.println(firstName+" "+middleInitial+" "+lastName);
    	System.out.println(address+" "+city+" "+state+" "+zip);
    	System.out.println("Accounts: ");
    	this.viewAccounts();
    }
    
}