package com.revature.app;

import java.util.Queue;
import java.util.ArrayList;

public class User {
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected Character middleInitial;
    protected Integer age;
    
    protected ArrayList <Account> accounts;
    protected static Queue <Application> applications;

    User () {}
    public User (String username, String password) {
        this.username = username;
        this.password = password;
        
    }
    
    public User(String firstName, String lastName, String address, Character middleInitial, Integer age,
			ArrayList<Account> accounts) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.middleInitial = middleInitial;
		this.age = age;
		this.accounts = accounts;
	}
	public boolean checkPassword (String password) {
    	return this.password.equals(password);
    }
    
    public boolean apply () {
    	UserInput.apply();
    	
    	return true;
    }

}