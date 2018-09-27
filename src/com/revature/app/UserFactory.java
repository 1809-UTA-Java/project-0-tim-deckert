package com.revature.app;

import java.util.HashSet;

public class UserFactory {
    private static HashSet<String> users = new HashSet<>();
    public static User createUser() {
        String uname;
        String pword;
        System.out.println("Thank you for choosing Tim's bank");
        System.out.println("Please enter a username and password");

        System.out.print("Username: ");
        uname = UserInput.getData();
        while(users.contains(uname)) {
            System.out.println("Sorry, that username is taken. Please choose a new one.");
            System.out.print("Username: ");
            uname = UserInput.getData();
        }

        do {
            System.out.print("Password: ");
            pword = UserInput.getData();
            System.out.print("Confirm Password: ");
            if (UserInput.getData().equals(pword)) {
                break;
            }
            System.out.println("Oops! Those didn't match. Try again.");
        } while (true);

        users.add(uname);
        return new User(uname, pword);
    }
    // public static Employee createEmployee() {
        
    //     return new Employee();
    // }
    // public static Admin createAdmin() {return new Admin();}
}