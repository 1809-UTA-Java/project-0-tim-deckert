package com.revature.resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.app.User;

public class UserDao {
	public ArrayList<User> getUsersEmployee(Integer empId) {
		PreparedStatement ps = null;
		User user = null;
		ArrayList<User> users = new ArrayList<>();
		
		try(Connection conn = JDBCUtil.getConnection()) {
			String sql = "SELECT * FROM USERS WHERE E_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String uname = rs.getString("uname");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String minit = rs.getString("minit");
				String address = rs.getString("street_address");
				String city = rs.getString("city");
				String state = rs.getString("fname");
				Integer zip = rs.getInt("zip");
				
				user = new User (uname, fname, lname, minit.charAt(0), address, city, state, zip);
				users.add(user);
				
			} 

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return users;
	}
	public ArrayList<User> getUsersAdmin(Integer empId) {
		PreparedStatement ps = null;
		User user = null;
		ArrayList<User> users = new ArrayList<>();
		
		try(Connection conn = JDBCUtil.getConnection()) {
			String sql = "SELECT * FROM USERS";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String uname = rs.getString("uname");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String minit = rs.getString("minit");
				String address = rs.getString("street_address");
				String city = rs.getString("city");
				String state = rs.getString("fname");
				Integer zip = rs.getInt("zip");
				
				user = new User (uname, fname, lname, minit.charAt(0), address, city, state, zip);
				users.add(user);
				
			} 

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return users;
	}
	public User getUser(String username) {
		PreparedStatement ps = null;
		User user = null;
		
		try(Connection conn = JDBCUtil.getConnection()) {
			String sql = "SELECT * FROM USERS WHERE uname = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String uname = rs.getString("uname");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String minit = rs.getString("minit");
				String address = rs.getString("street_address");
				String city = rs.getString("city");
				String state = rs.getString("fname");
				Integer zip = rs.getInt("zip");
				
				user = new User (uname, fname, lname, minit.charAt(0), address, city, state, zip);		
			} 

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return user;
	}
}
