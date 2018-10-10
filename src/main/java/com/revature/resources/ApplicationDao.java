package com.revature.resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

import com.revature.app.Application;

public class ApplicationDao {
	public LinkedBlockingQueue<Application> getApplications () {
		PreparedStatement ps = null;
		Application app = null;
		LinkedBlockingQueue<Application> applications = new LinkedBlockingQueue<>();
		
		try(Connection conn = JDBCUtil.getConnection()) {
			String sql = "SELECT * FROM USERS INNER JOIN APPLICATIONS ON USERS.UNAME = APPLICATIONS.CUSTOMER";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer appNo = rs.getInt("APP_ID");
				String uname = rs.getString("uname");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String minit = rs.getString("minit");
				String address = rs.getString("street_address");
				String city = rs.getString("city");
				String state = rs.getString("fname");
				Integer zip = rs.getInt("zip");
				
				app = new Application (appNo, uname, fname, lname, minit.charAt(0), address, city, state, zip);
				applications.add(app);
				
			}

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return applications;
	}
}
