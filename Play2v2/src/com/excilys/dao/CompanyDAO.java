package com.excilys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.bean.Company;
import com.excilys.bean.Computer;

public class CompanyDAO implements ICompanyDAO{

	@Override
	public List<Company> getCompany() {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = Connexion.getConnexion();
		List<Company> liste = new ArrayList<Company>();
		try {
			stmt = cn.createStatement();
			rs = stmt.executeQuery("SELECT IDCOMPANY, NAMECOMPANY FROM COMPANY ORDER BY NAMECOMPANY ASC;");
			while (rs.next()) {
				Company c = new Company();
				c.setIdCompany(rs.getInt(1));
				c.setNameCompany(rs.getString(2));
				liste.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs,stmt,cn);
		}
		return liste;
	}

	@Override
	public Company getCompanyByID(int i) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = Connexion.getConnexion();
		Company c = new Company();
		try {
			stmt = cn.createStatement();
			rs = stmt
					.executeQuery("SELECT IDCOMPANY, NAMECOMPANY "
							+ "FROM COMPANY WHERE IDCOMPANY="+ i);
			while (rs.next()) {
				c.setIdCompany(rs.getInt(1));
				c.setNameCompany(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs, stmt, cn);
		}
		return c;
	}

}
