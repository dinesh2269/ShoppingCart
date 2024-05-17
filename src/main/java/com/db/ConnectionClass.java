package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
	public Connection getconnectDb() throws ClassNotFoundException, SQLException {
		String jdbcUrl = "jdbc:postgresql://192.168.110.48:5432/plf_training";
		String username = "plf_training_admin";
		String password = "pff123";

		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
		return conn;
	}
}
