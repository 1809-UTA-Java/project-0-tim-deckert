package com.revature.resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.app.Admin;
import com.revature.app.Employee;
import com.revature.app.User;

public class UserFactory {
    public static User createUser() {
    	String[] user_pass = UserInput.getNewUser();
    	return new User(user_pass[0], user_pass[1]);        
    }
    
    public static User createUser(String uname, String pword) {
    	return new User(uname, pword);
    }
    
    public static Employee createEmployee() {
     	String[] user_pass = UserInput.getNewUser();
     	Employee employee = new Employee(user_pass[0], user_pass[1]);
    	return employee;
     }
    
     public static Admin createAdmin() {
     	 String[] user_pass = UserInput.getNewUser();
     	 Admin admin = new Admin(user_pass[0], user_pass[1]);
     	 return admin;
     }
}