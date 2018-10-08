package com.revature.resources;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;
import com.revature.app.*;

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
        byte[] passHash = user_pass[1].getBytes();
        
        try {
        	MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(passHash);
            user_pass[1] = md.digest().toString();
        } catch (Exception ex) {
        	ex.getMessage();
        }
        
        return user_pass;
    }
    
    public static String[] getLoginInfo() {
        String[] user_pass = new String[2];
        System.out.println("LOGIN");

        System.out.print("Username: ");
        user_pass[0] = sc.nextLine();
        
        System.out.print("Password: ");
        byte[] passHash = sc.nextLine().getBytes();
        
        try {
        	MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(passHash);
            user_pass[1] = md.digest().toString();
        } catch (Exception ex) {
        	ex.getMessage();
        }
        
        return user_pass;
    }

    public static ArrayList<String> apply() {
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
        	System.out.print("State postal code: ");
        	data.add(sc.nextLine());
        	System.out.print("Zip: ");
        	data.add(sc.nextLine());
        	
        	System.out.println("Now let's add the second user.");
        	System.out.print("Does the person you're filing with have a Username? (yes/no): ");
        	
        	while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
        		System.out.print("Only answer yes or no, please: ");
        		answer = sc.nextLine();
        	}
        	
        	if (answer.equalsIgnoreCase("no")) {
        		UserFactory.createUser();
        	}
        	
        	User us = Login.login();
        	
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
    	System.out.print("State postal code: ");
    	data.add(sc.nextLine());
    	System.out.print("Zip: ");
    	data.add(sc.nextLine());
    	
    	return data;
    }
}