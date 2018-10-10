package com.revature.resources;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.app.User;

public class Users implements Serializable {
    //private static HashMap <String, User> masterUserList = new HashMap<>();

    public static boolean contains(String uname) {
    	PreparedStatement ps = null;
    	//System.out.println("we in contains1 "+uname);
    	try (Connection conn = JDBCUtil.getConnection()) {
    		String sql = "SELECT UNAME FROM USERS WHERE UNAME = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, uname);
        	ResultSet rs = ps.executeQuery();
        	
        	//ps.close();
        	
        	if (rs.next()) {
        		rs.close();
        		return true;
        	}
        	rs.close();
        	return false;
        	
        } catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
    	}
        return false;
    }
    
    public static boolean contains(String uname, String pword) {
    	PreparedStatement ps = null;
    	//System.out.println("we in contains2"+uname+" "+pword);
    	try (Connection conn = JDBCUtil.getConnection()) {
    		String sql = "SELECT UNAME, PWORD FROM USERS WHERE UNAME = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, uname);
        	ResultSet rs = ps.executeQuery();
        	
        	if (rs.next()) {
        		return rs.getString("UNAME").equals(uname) && rs.getString("PWORD").equals(pword);
        	}
        	rs.close();
        	return false;
        	
        } catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
    	}
        return false;
    }
    
    public static User verify(String uname, String pword) {
    	User user = null;
    	if (Users.contains(uname, pword)) {
    		try (Connection conn = JDBCUtil.getConnection()) {
    			String sql = "SELECT EMPLOYEE.PERMISSION FROM USERS INNER JOIN EMPLOYEE ON EMPLOYEE.UNAME = USERS.UNAME WHERE UNAME = ?";
    			PreparedStatement ps = conn.prepareStatement(sql);
            	ps.setString(1, uname);
            	ResultSet rs = ps.executeQuery();
            	
            	if (rs.next()) {
            		char perm = rs.getString("PERMISSION").charAt(0);
            		if (perm == 'A') {
            			user = UserFactory.createAdmin(uname, pword);
            		}
            		else if (perm == 'E') {
            			user = UserFactory.createEmployee(uname, pword);
            		}
            	}
            	else {
            		user = UserFactory.createUser(uname, pword);
            	}
            	rs.close();
            	
            } catch (SQLException ex) {
            	ex.getMessage();
            } catch (IOException e) {
            	e.getMessage();
        	}
    	}
    	
    	return user;
    }

}