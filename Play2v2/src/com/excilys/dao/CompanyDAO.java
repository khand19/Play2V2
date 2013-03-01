package com.excilys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.bean.Company;

public enum CompanyDAO implements ICompanyDAO {
	INSTANCE;

	private static final String SELECT_ID = "SELECT IDCOMPANY, NAMECOMPANY FROM COMPANY WHERE IDCOMPANY=";
	private static final String SELECT_ASC = "SELECT IDCOMPANY, NAMECOMPANY FROM COMPANY ORDER BY NAMECOMPANY ASC;";

	@Override
	public List<Company> getCompany() {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = DataSourceFactory.INSTANCE.getThreadConnexion();
		List<Company> liste = new ArrayList<Company>();
		try {
			stmt = cn.createStatement();
			rs = stmt.executeQuery(SELECT_ASC);
			while (rs.next()) {
				Company c = new Company();
				c.setIdCompany(rs.getInt(1));
				c.setNameCompany(rs.getString(2));
				liste.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return liste;
	}

	@Override
	public Company getCompanyByID(int i) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = DataSourceFactory.INSTANCE.getThreadConnexion();
		Company c = new Company();
		try {
			stmt = cn.createStatement();
			rs = stmt.executeQuery(SELECT_ID + i);
			while (rs.next()) {
				c.setIdCompany(rs.getInt(1));
				c.setNameCompany(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return c;
	}

}
