package com.revature.resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection() throws SQLException, IOException {
		String url = "jdbc:oracle:thin:@192.168.56.105:1521:xe";
		String username = "bank";
		String password = "p4ssw0rd";
		
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		return DriverManager.getConnection(url, username, password);
		
	}
}
