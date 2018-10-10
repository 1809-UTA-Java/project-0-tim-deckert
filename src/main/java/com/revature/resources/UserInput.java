package com.revature.resources;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.app.Admin;
import com.revature.app.Application;
import com.revature.app.Employee;
import com.revature.app.User;

public class UserInput {
    private static Scanner sc = new Scanner(System.in);
    private UserInput () {}

    public static void closeInput() {
        sc.close();
    }

    public static String getData(String message) {
        String data = null;
        System.out.print(message);
        data = sc.nextLine();
        return data;
    }
    
    public static String[] getNewUser() {
        String[] user_pass = new String[2];
        System.out.println("Thank you for choosing Tim's bank");
        System.out.println("Please enter a username and password");

        System.out.print("Username: ");
        user_pass[0] = sc.nextLine();
        while(Users.contains(user_pass[0])) {
            System.out.println("Sorry, that username is taken. Please choose a new one.");
            System.out.print("Username: ");
            user_pass[0] = sc.nextLine();
        }

        do {
            System.out.print("Password: ");
            user_pass[1] = sc.nextLine();
            System.out.print("Confirm Password: ");
            if (sc.nextLine().equals(user_pass[1])) {
                break;
            }
            System.out.println("Oops! Those didn't match. Try again.");
        } while (true);
        /*byte[] passHash = user_pass[1].getBytes();
        //System.out.println(Arrays.toString(passHash));
        try {
        	MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(passHash);
            user_pass[1] = new String(md.digest());
            //System.out.println(user_pass[1]);
        } catch (Exception ex) {
        	ex.getMessage();
        }*/
        
        return user_pass;
    }
    
    public static String[] getLoginInfo() {
        String[] user_pass = new String[2];
        System.out.println("LOGIN");

        System.out.print("Username: ");
        user_pass[0] = sc.nextLine();
        
        System.out.print("Password: ");
        user_pass[1] = sc.nextLine();
        /*byte[] passHash = user_pass[1].getBytes();
        //System.out.println(Arrays.toString(passHash));
        try {
        	MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(passHash);
            user_pass[1] = new String(md.digest());
            //System.out.println(user_pass[1]);
        } catch (Exception ex) {
        	ex.getMessage();
        }*/
        
        return user_pass;
    }

    public static void apply(String uname) {
    	ArrayList<Application> apps = new ArrayList<>();
    	ArrayList<String> data = new ArrayList<>();
    	System.out.println("Thank you for applying for a Tim's Bank account");
    	System.out.println("We need some more information to get started");
    	System.out.print("Are you filing for a joint account? If you are unsure, don't worry. "
    					 + "People can be added to your account later. (yes/no): ");
    	String answer = sc.nextLine();
    	
    	while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
    		System.out.print("Only answer yes or no, please: ");
    		answer = sc.nextLine();
    	}
    	data.add(uname);
    	if (answer.equalsIgnoreCase("yes")) {
    		System.out.println("Please enter your");
    		System.out.print("First name: ");
        	data.add(sc.nextLine());
        	System.out.print("Last name: ");
        	data.add(sc.nextLine());
        	System.out.print("Middle Initial: ");
        	data.add(sc.nextLine());
        	System.out.print("Street Address: ");
        	data.add(sc.nextLine());
        	System.out.print("City: ");
        	data.add(sc.nextLine());
        	do {
	        	System.out.print("Two letter state abbreviation: "); 
	        	data.add(sc.nextLine());
        	} while (data.get(data.size()-1).length() > 2);
        	System.out.print("Zip: ");
        	data.add(sc.nextLine());
        	
        	apps.add(new Application(data));
        	data.clear();
        	
        	System.out.println("Now, let's add the second user.");
        	System.out.print("Does the person you're filing with have a Username? (yes/no): ");
        	
        	while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
        		System.out.print("Only answer yes or no, please: ");
        		answer = sc.nextLine();
        	}
        	
        	if (answer.equalsIgnoreCase("no")) {
        		UserFactory.createUser();
        	}
        	
        	User us = Login.login();
        	data.add(us.getUsername());
        	
    	}
    	
    	System.out.print("First name: ");
    	data.add(sc.nextLine());
    	System.out.print("Last name: ");
    	data.add(sc.nextLine());
    	System.out.print("Middle Initial: ");
    	data.add(sc.nextLine());
    	System.out.print("Street Address: ");
    	data.add(sc.nextLine());
    	System.out.print("City: ");
    	data.add(sc.nextLine());
    	do {
        	System.out.print("Two letter state abbreviation: "); 
        	data.add(sc.nextLine());
    	} while (data.get(data.size()-1).length() > 2);
    	
    	System.out.print("Zip: ");
    	data.add(sc.nextLine());
    	
    	apps.add(new Application(data));
    	
    }

    public static void userPortal(User user) {
    	int choice = 0;
    	while (choice != 6) {
	    	System.out.println("Welcome to the customer portal. What would you like to do?");
	    	System.out.println("(1) view existing accounts");
	    	System.out.println("(2) apply for a new account");
	    	System.out.println("(3) deposit funds into an account");
	    	System.out.println("(4) withdraw funds from an account");
	    	System.out.println("(5) transfer funds from one account to another");
	    	System.out.println("(6) to exit");
	    	System.out.println();
	    	
	    	choice = sc.nextInt();
	    	sc.nextLine();
	    	switch (choice) {
			case 1:
				user.viewAccounts();
				break;
			case 2:
				user.apply();
				break;
			case 3:
				user.deposit();
				break;
			case 4:
				user.withdraw();
				break;
			case 5:
				user.transfer();
				break;
				default:
					System.out.println("Goodbye");
			}
    	}
    }
    
    public static void employeePortal(Employee e) {
    	int choice = 0;
    	while (choice != 5) {
	    	System.out.println("Welcome to the employee portal. What would you like to do?");
	    	System.out.println("(1) view all of your customers");
	    	System.out.println("(2) view one of your customers");
	    	System.out.println("(3) approve/deny applications");
	    	System.out.println("(4) enter a customer portal");
	    	System.out.println("(5) to exit");
	    	System.out.println();
	    	
	    	choice = sc.nextInt();
	    	sc.nextLine();
	    	switch (choice) {
			case 1:
				e.viewUsers();
				break;
			case 2:
				e.viewUser();
				break;
			case 3:
				e.approveApplication();
				break;
			case 4:
				UserInput.userPortal(e);
				break;
				default:
					System.out.println("Goodbye");
	    	}
    	}
    }
    public static void adminPortal(Admin a) {
    	int choice = 0;
    	while (choice != 7) {
	    	System.out.println("Welcome to the administrator portal. What would you like to do?");
	    	System.out.println("(1) view all customers' info");
	    	System.out.println("(2) view one customer's info");	    	
	    	System.out.println("(3) approve/deny applications");
	    	System.out.println("(4) alter accounts");
	    	System.out.println("(5) alter employees");
	    	System.out.println("(6) enter a customer portal");
	    	System.out.println("(7) to exit");
	    	System.out.println();
	    	
	    	choice = sc.nextInt();
	    	sc.nextLine();
	    	switch (choice) {
			case 1:
				a.viewUsers();
				break;
			case 2:
				a.viewUser();
				break;
			case 3:
				a.approveApplication();
				break;
			case 4:
				UserInput.adminAcc(a);
				break;
			case 5:
				UserInput.adminEmp(a);
			case 6:
				UserInput.userPortal(a);
				break;
			case 7:
				return;
	    	}
    	}
    }
    
    public static void adminAcc(Admin a) {
    	int choice = 0;
    	while (choice != 6) {
	    	System.out.println("(1) view all existing accounts");
	    	System.out.println("(2) deposit funds into an account");
	    	System.out.println("(3) withdraw funds from an account");
	    	System.out.println("(4) transfer funds from one account to another");
	    	System.out.println("(5) cancel a bank account");
	    	System.out.println("(6) back to main admin portal");
	    	choice = sc.nextInt();
	    	sc.nextLine();
	    	switch (choice) {
			case 1:
				a.viewAccounts();
				break;
			case 2:
				a.deposit();
				break;
			case 3:
				a.withdraw();
				break;
			case 4:
				a.transfer();
				break;
			case 5:
				a.delete();
				break;
			case 6:
				return;
	    	}
		}
    }

    public static void adminEmp(Admin a) {
    	int choice = 0;
    	while (choice != 5) {
    		System.out.println("(1) view all employees");
    		System.out.println("(2) hire employee");
	    	System.out.println("(3) hire admin");
	    	System.out.println("(4) fire employee");
	    	System.out.println("(5) to exit");
	    	
	    	choice = sc.nextInt();
	    	sc.nextLine();
	    	
	    	switch (choice) {
	    	case 1:
	    		a.viewEmployees();
	    	case 2:
				a.hireEmployee();
				break;
			case 3:
				a.hireAdmin();
				break;
			case 4:
				a.fireEmployee();
				break;
			case 5:
				return;
	    	}
			
    	}
    }
}