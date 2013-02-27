package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.excilys.bean.Log;

public class LogDAO implements ILogDAO{
	private static final String SELECT_ALL = "SELECT IDLOG,DATELOG,OPTIONLOG,COMPUTERLOG FROM LOG ORDER BY DATELOG ASC";
	private static final String INSERT = "INSERT INTO LOG SET DATELOG=?,OPTIONLOG=?,COMPUTERLOG=?";

	
	@Override
	public List<Log> getLog() {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = Connexion.getConnexion();
		List<Log> liste = new ArrayList<Log>();
		try {
			stmt = cn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Log l = new Log();
				l.setIdLog(rs.getInt(1));
				l.setDateLog((Date)rs.getDate(2));
				l.setOptionLog(rs.getString(3));
				l.setComputerLog(rs.getString(4));
				liste.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs, stmt, cn);
		}
		return liste;
	}

	@Override
	public void addLog(Log l,Connection c) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = Connexion.getConnexion();
		try {
			stmt = cn.prepareStatement(new String(INSERT));
			stmt.setObject(1, l.getDateLog());
			stmt.setString(2, l.getOptionLog());
			stmt.setString(3, l.getComputerLog());	
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs, stmt, cn);
		}			
	}

}