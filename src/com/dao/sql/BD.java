package com.dao.sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
	public static Connection con = null;
	
	private BD() {}

	public static void conectar() throws SQLException {
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DAGIRALDO", "dagiraldo");
	}
	
	public static void close() throws SQLException {
		if(con != null) {
			con.close();
		}
	}
	
}
