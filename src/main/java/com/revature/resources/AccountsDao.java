package com.revature.resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.app.Account;

public class AccountsDao {
	public ArrayList<Account> getAccountsUser(String uname) {
		PreparedStatement ps = null;
		Account acc = null;
		ArrayList<Account> accounts = new ArrayList<>();
		
		try(Connection conn = JDBCUtil.getConnection()) {
			String sql = "SELECT ACCOUNTS.A_ID, ACCOUNTS.E_ID, ACCOUNTS.FUNDS "
					+ "FROM USERS INNER JOIN USER_ACCOUNTS ON USERS.UNAME = USER_ACCOUNTS.UNAME"
					+ "INNER JOIN ACCOUNTS ON USER_ACCOUNTS.A_ID = ACCOUNTS.A_ID";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer accountId = rs.getInt("A_ID");
				Integer employeeId = rs.getInt("E_ID");
				double funds = rs.getDouble("funds");
				
				acc = new Account (accountId, employeeId, funds);
				accounts.add(acc);
				
			} 
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return accounts;
	}
	
	public ArrayList<Account> getAccountsAdmin(String uname) {
		PreparedStatement ps = null;
		Account acc = null;
		ArrayList<Account> accounts = new ArrayList<>();
		
		try(Connection conn = JDBCUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNTS";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer accountId = rs.getInt("A_ID");
				Integer employeeId = rs.getInt("E_ID");
				double funds = rs.getDouble("funds");
				
				acc = new Account (accountId, employeeId, funds);
				accounts.add(acc);
				
			} 
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return accounts;
	}
	public Account getAccount(Integer accId) {
		PreparedStatement ps = null;
		Account acc = null;
		
		try(Connection conn = JDBCUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNTS WHERE A_ID = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, accId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer accountId = rs.getInt("A_ID");
				Integer employeeId = rs.getInt("E_ID");
				double funds = rs.getDouble("funds");
				
				acc = new Account (accountId, employeeId, funds);
				
			} 
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return acc;
	}
	public void deleteAccount(Integer accId) {
		PreparedStatement ps = null;
		Account acc = null;
		
		try(Connection conn = JDBCUtil.getConnection()) {
			String sql = "DELETE FROM ACCOUNTS WHERE A_ID = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, accId);
			ps.executeQuery();
			
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
	}
}
