package com.revature.resources;

import com.revature.app.*;

public class Login {
	public static User login() {
		String[] user_pass = new String[2];
		User loggingIn;
		
		user_pass = UserInput.getLoginInfo();
		loggingIn = Users.verify(user_pass[0], user_pass[1]);
		while (loggingIn == null) {
			UserInput.getData("That username and password combination is not correct\n(Press Enter to continue)");
			user_pass = UserInput.getLoginInfo();
			loggingIn = Users.verify(user_pass[0], user_pass[1]);
		}
		
		return loggingIn;
	}
}