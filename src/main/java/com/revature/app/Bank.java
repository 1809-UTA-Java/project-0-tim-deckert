package com.revature.app;

import com.revature.resources.*;

class Bank {
    public static void main(String[] args) {
        //System.out.print(IntroMessage);
    	
    	String answer = UserInput.getData("Welcome to Tim's bank.\nAre you a new user?(yes/no) ");
    	
    	while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
    		answer = UserInput.getData("Only answer yes or no, please: ");
    	}
        if(answer.equalsIgnoreCase("yes")) {
            UserFactory.createUser();
            UserInput.userPortal(Login.login());
        }
        else {
        	User user = Login.login();
        	if (user instanceof Admin) {
        		UserInput.adminPortal((Admin) user);
        	}
        	else if (user instanceof Employee) {
        		UserInput.employeePortal((Employee) user); 
        	}
        	else {
        		UserInput.userPortal(user);
        	}
        }
        UserInput.closeInput();
    }
}