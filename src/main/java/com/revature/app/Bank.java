package com.revature.app;

import com.revature.resources.*;

class Bank {
    final private static String IntroMessage = "Welcome to Tim's bank.\nAre you a new user?(y/n) ";
    public static void main(String[] args) {
        //System.out.print(IntroMessage);
        if(UserInput.getData(IntroMessage).equals("y")) {
            UserFactory.createUser();
        }
        else {
        	User user = Login.login();
        	if (user instanceof Admin) {
        		
        	}
        	else if (user instanceof Employee) {
        		
        	}
        	else {
        		
        	}
        }
        UserInput.closeInput();
    }
}