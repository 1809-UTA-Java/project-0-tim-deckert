package com.revature.resources;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class JDBCUtil {
	public static Connection getConnection() throws SQLException, IOException {
		String url = "jdbc:oracle:thin:@192.168.56.105:1521:xe";
		String username = "bank";
		String password = "p4ssw0rd";
		
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void defaultUser () {
		String pass = null;
		try (Connection conn = JDBCUtil.getConnection()) {
			//byte[] passHash = "changeme".getBytes();
	        /*try {
	        	MessageDigest md = MessageDigest.getInstance("SHA-256");
	            md.update(passHash);
	            pass = new String(md.digest());
	        } catch (Exception ex) {
	            ex.getMessage();
	        }*/
	        
	        PreparedStatement ps = null;
    		String sql = "INSERT INTO USERS (UNAME, PWORD) VALUES ('root', 'changeme')";
        	ps = conn.prepareStatement(sql);
        	//ps.setString(1, pass);
        	ps.executeQuery();
        	ps.close();
        	
        } catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
    	}
		
		try (Connection conn = JDBCUtil.getConnection()) {
			String sql = "INSERT INTO EMPLOYEE (UNAME, PERMISSION) VALUES ('root', 'A')";
			
	        PreparedStatement ps = null;
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery();
        	ps.close();
        	
		} catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
    	}
		
	}
}
