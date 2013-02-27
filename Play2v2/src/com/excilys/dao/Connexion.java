package com.excilys.dao;

import java.sql.*;

public class Connexion {

	static {
        try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnexion() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/ordinateur",
					"sa", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeAll(ResultSet rs, Statement stmt, Connection cn) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (cn != null)
				cn.close();
		} catch (SQLException e) {
		}
	}
}
