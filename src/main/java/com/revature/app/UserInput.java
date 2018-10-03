package com.revature.app;

import java.util.Scanner;
import java.security.MessageDigest;
import java.util.ArrayList;

class UserInput {
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
    	
    	System.out.print("First name: ");
    	data.add(sc.nextLine());
    	System.out.print("Last name: ");
    	data.add(sc.nextLine());
    	System.out.print("Middle Initial: ");
    	data.add(sc.nextLine());
    	System.out.print("Address: ");
    	data.add(sc.nextLine());
    	System.out.print("Age: ");
    	data.add(sc.nextLine());
    	
    	return data;
    }
}