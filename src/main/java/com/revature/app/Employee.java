package com.revature.app;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import com.revature.resources.ApplicationDao;
import com.revature.resources.JDBCUtil;
import com.revature.resources.UserDao;
import com.revature.resources.UserInput;

import oracle.jdbc.OracleTypes;

public class Employee extends User {
	protected Integer employeeID;
	protected static LinkedBlockingQueue<Application> applications = new LinkedBlockingQueue<>();
	protected ArrayList<User> users; 
	public Employee(String username, String password) {
		super(username, password);
			
		PreparedStatement ps = null;
		
		try (Connection conn = JDBCUtil.getConnection()) {
        	String sql = "INSERT INTO EMPLOYEE (UNAME, PERMISSION) VALUES (?, 'E')";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, username);
        	//ps.setString(2, password);
        	ps.executeQuery();
        	ps.close();	
        	
        } catch (SQLException ex) {
        	ex.getMessage();
        } catch (IOException e) {
        	e.getMessage();
        }
		
	}

	public Employee(Integer employeeID, String username) {
		this.employeeID = employeeID;
		this.username = username;
	}
	
	public void displayEmployee () {
		System.out.println("Employee ID: "+employeeID);
		super.displayUser();
	}
	
	public void pullApplications() {
		if (applications.size() == 0) {
			ApplicationDao appd = new ApplicationDao();
			applications = appd.getApplications();
		}
	}
	
	public void pullUsers() {
		UserDao userd = new UserDao();
		users = userd.getUsersEmployee(this.employeeID);
	}
	
	public void viewUsers() {
		this.pullUsers();
		int i = 1;
    	for (User u: users) {
    		System.out.print(i++ +") ");
    		u.displayUser();
    	}
	}
	
	public void viewUser() {
		String uname = UserInput.getData("Enter the username you want to view");
		UserDao userd = new UserDao();
		userd.getUser(uname).displayUser();
	}
	
	public Application getApplication() {
		if (applications.size() == 0) {
			this.pullApplications();
		}
		Application app = applications.poll();
		int i = 0;
		while (this.username.equals(app.getUser()) && i < 10) {
			++i;
			
			try {
				applications.put(app);
				app = applications.poll();
			} catch (InterruptedException ex) {
				ex.getMessage();
			}
		}
		if (i >= 10) {
			return null;
		}
		return app;
	}
	
	public boolean approveApplication() {
		Application toApprove = getApplication();
		String answer;
		if (toApprove == null) {
			UserInput.getData("No applications to approve at this time");
			return false;
		}
		
		toApprove.viewApplication();
		answer = UserInput.getData("Do you want to approve this application? (yes/no) ");
		
		while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
    		System.out.print("Only answer yes or no, please: ");
    		answer = UserInput.getData(" ");
    	}
		if (answer.equalsIgnoreCase("yes")) {
			Integer accountNumber = 0;
			CallableStatement cs = null;
			PreparedStatement ps = null;
			String sql = "{ ? = CALL INSERT_ACCOUNT(?) }";
			
					//"INSERT INTO ACCOUNTS (E_ID, FUNDS) VALUES (?, 0.00)";
			
			try (Connection conn = JDBCUtil.getConnection()) {
				cs = conn.prepareCall(sql);
				cs.registerOutParameter(1, OracleTypes.INTEGER);
				cs.setLong(2, this.employeeID);
				
				cs.execute();
				accountNumber = cs.getInt(1);
				cs.close();
			} catch (SQLException ex) {
	        	ex.getMessage();
	        } catch (IOException e) {
	        	e.getMessage();
	        }
			
			try (Connection conn = JDBCUtil.getConnection()) {
				sql = "INSERT INTO USER_ACCOUNTS VALUES (?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, toApprove.getUser());
				ps.setInt(2, accountNumber);
				
				ps.executeUpdate();
				ps.close();
			} catch (SQLException ex) {
	        	ex.getMessage();
	        } catch (IOException e) {
	        	e.getMessage();
	        }
			
			try (Connection conn = JDBCUtil.getConnection()) {
				sql = "UPDATE USERS SET (E_ID = ?) WHERE UNAME = ?";
				ps.setInt(1, this.employeeID);
				ps.setString(2, toApprove.getUser());
				
				ps.executeUpdate();
				ps.close();
			} catch (SQLException ex) {
	        	ex.getMessage();
	        } catch (IOException e) {
	        	e.getMessage();
	        }
			
			return true;
		}
		else {
			PreparedStatement ps = null;
			String sql = "DELETE FROM APPLICATIONS WHERE APP_ID = ?";
			try (Connection conn = JDBCUtil.getConnection()) {
				ps.setInt(1, toApprove.getAppNo());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException ex) {
	        	ex.getMessage();
	        } catch (IOException e) {
	        	e.getMessage();
	        }
			return false;
		}
		
	}
}