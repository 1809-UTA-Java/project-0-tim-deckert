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
    	try (Connection conn = JDBCUtil.getConnection()) {
    		String sql = "SELECT UNAME FROM USERS WHERE UNAME = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, uname);
        	ResultSet rs = ps.executeQuery();
        	ps.close();
        	
        	if (rs != null) {
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
    	try (Connection conn = JDBCUtil.getConnection()) {
    		String sql = "SELECT UNAME, PWORD FROM USERS WHERE UNAME = ?, PWORD = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, uname);
        	ResultSet rs = ps.executeQuery();
        	ps.close();
        	
        	if (rs != null) {
        		rs.next();
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
    	
    	if (Users.contains(uname, pword)) {
    		User user = UserFactory.createUser(uname, pword);
    		return user;
    	}
    	return null;
    }

}