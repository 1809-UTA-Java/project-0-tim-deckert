package com.revature.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.resources.JDBCUtil;

public class Application {
	private Integer applicationNumber;
	private String userName;
	private String firstName;
    private String lastName;
    private Character middleInitial;
    private String address;
    private String city;
    private String state;
    private Integer zip;
    
    public Application () {}
    
	public Application(Integer applicationNumber, String userName, String firstName, String lastName, Character middleInitial, String address,
			String city, String state, Integer zip) {
		super();
		this.applicationNumber = applicationNumber;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		
		/*PreparedStatement ps = null;
		
		try (Connection conn = JDBCUtil.getConnection()) {
        	String sql = "INSERT INTO APPLICATIONS (CUSTOMER) VALUES (?)";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, userName);
        	//ps.setString(2, password);
        	ps.executeQuery();
        	ps.close();
        	
        } catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
        }*/
        
	}
	
	public Application(ArrayList<String> entries) {
		if (entries.size() == 8) {
			this.userName = entries.get(0);
			this.firstName = entries.get(1);
			this.lastName = entries.get(2);
			this.middleInitial = entries.get(3).charAt(0);
			this.address = entries.get(4);
			this.city = entries.get(5);
			this.state = entries.get(6);
			this.zip = Integer.parseInt(entries.get(7));	
		}
		
		PreparedStatement ps = null;
		
		try (Connection conn = JDBCUtil.getConnection()) {
        	String sql = "INSERT INTO APPLICATIONS (CUSTOMER) VALUES (?)";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, userName);
        	//ps.setString(2, password);
        	ps.executeQuery();
        	ps.close();	
        	
        } catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
        }
		
		try (Connection conn = JDBCUtil.getConnection()) {
	    	String sql = "UPDATE USERS SET (FNAME = ?, LNAME = ?, MINIT = ?, ADDRESS = ?, CITY = ?, STATE = ?, ZIP = ?) WHERE UNAME = ? ";
	    	ps = conn.prepareStatement(sql);
	    	
	    	ps.setString(8, userName);
	    	ps.setString(1, firstName);
	    	ps.setString(2, lastName);
	    	ps.setLong(3, middleInitial);
	    	ps.setString(4, address);
	    	ps.setString(5, city);
	    	ps.setString(6, state);
	    	ps.setLong(7, zip);
	    	ps.executeQuery();
	    	
	    	ps.close();
		} catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
        }
	}
	
	public void viewApplication () {
    	System.out.println(firstName+" "+middleInitial+" "+lastName);
    	System.out.println(address+" "+city+" "+state+" "+zip);
    }
	
	public String getUser() {
		return userName;
	}
	
	public Integer getAppNo() {
		return applicationNumber;
	}
}