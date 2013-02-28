package com.excilys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCPDataSource;

public enum DataSourceFactory {
	INSTANCE;

	private static final String PASSWORD = "";
	private static final String USER = "sa";
	private static final String URL = "jdbc:h2:tcp://localhost/~/ordinateur";
	protected static BoneCPDataSource connectionPool;
	protected ThreadLocal<Connection> monThreadConnexion = new ThreadLocal<Connection>(); 
	
	
	private DataSourceFactory() {
		initialize();
	}

	public synchronized void initialize() {

		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 connectionPool = new BoneCPDataSource();
		 connectionPool.setJdbcUrl( URL );
		 connectionPool.setUsername( USER );
		 connectionPool.setPassword( PASSWORD );
		
		 connectionPool.setMinConnectionsPerPartition( 5 );
		 connectionPool.setMaxConnectionsPerPartition( 10 );
		 connectionPool.setPartitionCount( 2 );
	}

	public synchronized Connection getConnexion() {
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			System.err.println("Error in DataSourceFactory.getConn:"
					+ e.getMessage());
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
	
	public ThreadLocal<Connection> getMonThreadConnexion(){
		return monThreadConnexion;
	}
}
