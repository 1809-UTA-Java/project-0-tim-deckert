package com.revature.resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.app.Employee;

public class EmployeeDao {
	public ArrayList<Employee> getEmployees() {
		PreparedStatement ps = null;
		Employee employee = null;
		ArrayList<Employee> employees = new ArrayList<>();
		
		try(Connection conn = JDBCUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE INNER JOIN USERS ON USERS.UNAME = EMPLOYEE.UNAME";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer empId = rs.getInt("E_ID");
				String uname = rs.getString("uname");
				
				employee = new Employee (empId, uname);
				employees.add(employee);
				
			} 

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return employees;
	}
	public Employee getEmployee(Integer empId) {
		PreparedStatement ps = null;
		Employee employee = null;
		
		try(Connection conn = JDBCUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE E_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer empID = rs.getInt("E_ID");
				String uname = rs.getString("uname");
				
				employee = new Employee (empID, uname);		
			} 

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return employee;
	}
}
