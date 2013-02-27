package com.excilys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class Connexion {

	private static final String PASSWORD = "";
	private static final String USER = "sa";
	private static final String URL = "jdbc:h2:tcp://localhost/~/ordinateur";
	private static BoneCP connectionPool;

	static {
        try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
//			conn = DriverManager.getConnection(URL,
//					USER, PASSWORD);			
			
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl( URL );
			config.setUsername( USER );
			config.setPassword( PASSWORD );
			     
			config.setMinConnectionsPerPartition( 5 );
			config.setMaxConnectionsPerPartition( 10 );
			config.setPartitionCount( 2 );
			     
			connectionPool = new BoneCP( config );
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnexion() {
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
	
	public static void closeAll(ResultSet rs, Statement stmt) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
		}
	}
}
