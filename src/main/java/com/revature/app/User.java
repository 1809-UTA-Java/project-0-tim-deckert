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
    protected static Queue <Application> applications;

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
    
    
	public User(String username, String password, String firstName, String lastName, Character middleInitial,
			String address, String city, String state, Integer zip, ArrayList<Account> accounts) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.accounts = accounts;
	}
	
	public boolean checkPassword (String password) {
    	return this.password.equals(password);
    }
    
    public void apply () {
    	ArrayList <String> application = UserInput.apply();
    	
    	
    }

}