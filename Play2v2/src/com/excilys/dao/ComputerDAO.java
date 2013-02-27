package com.excilys.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.excilys.bean.Company;
import com.excilys.bean.Computer;

public class ComputerDAO implements IComputerDAO {
	private final int NB_EL_PAGE = 10;

	@Override
	public void addComputer(Computer pComputer) {
		StringBuilder req = new StringBuilder("INSERT INTO COMPUTER SET  ");
		if (!pComputer.equals(""))
			req.append(" NAME=?,");
		if (pComputer.getCompany().getIdCompany()!=0)
			req.append(" IDCOMPANY=?,");		
		if (pComputer.getIntroducedDate() == null)
			req.append(" INTRODUCED=null,");
		else if (pComputer.getIntroducedDate() != null)
			req.append(" INTRODUCED=?,");
		if (pComputer.getDscountedDate() == null)
			req.append(" DISCONTINUED=null,");
		else if (pComputer.getDscountedDate() != null)
			req.append(" DISCONTINUED=?,");
		req.deleteCharAt(req.length() - 1);
		
		int incr = 1;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = Connexion.getConnexion();
		try {
			stmt = cn.prepareStatement(new String(req));

			if (!pComputer.equals("")){
				stmt.setString(incr, pComputer.getNameComputer());
				incr++;
			}
			if (pComputer.getCompany().getIdCompany()!=0){
				stmt.setInt(incr, pComputer.getCompany().getIdCompany());
				incr++;
			}
			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			if (pComputer.getIntroducedDate() != null) {
				stmt.setDate(incr, java.sql.Date.valueOf(formatter
						.format(pComputer.getIntroducedDate())));
				incr++;
			}
			if (pComputer.getDscountedDate() != null) {
				stmt.setDate(incr, java.sql.Date.valueOf(formatter
						.format(pComputer.getDscountedDate())));
				incr++;
			}
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs, stmt, cn);
		}	
	}

	@Override
	public List<Computer> getComputers() {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = Connexion.getConnexion();
		List<Computer> liste = new ArrayList<Computer>();
		try {
			stmt = cn.createStatement();
			rs = stmt
					.executeQuery("SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY ORDER BY UPPER(c.NAME) ASC;");
			while (rs.next()) {
				Computer c = new Computer();
				c.setCompany(new Company(rs.getInt(5), rs.getString(6)));
				c.setIdComputer(rs.getInt(1));
				c.setNameComputer(rs.getString(2));
				c.setIntroducedDate(rs.getDate(3));
				c.setDscountedDate(rs.getDate(4));
				liste.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs, stmt, cn);
		}
		return liste;
	}

	@Override
	public Computer getComputerById(int pIdComputer) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = Connexion.getConnexion();
		Computer c = new Computer();
		try {
			stmt = cn.createStatement();
			rs = stmt
					.executeQuery("SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY "
							+ "FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY WHERE c.ID="
							+ pIdComputer);
			while (rs.next()) {
				c.setCompany(new Company(rs.getInt(5), rs.getString(6)));
				c.setIdComputer(rs.getInt(1));
				c.setNameComputer(rs.getString(2));
				c.setIntroducedDate(rs.getDate(3));
				c.setDscountedDate(rs.getDate(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs, stmt, cn);
		}
		return c;
	}

	@Override
	public Computer getComputerByName(String pNameComputer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComputer(int pIdComputer) {
		Connection cn = Connexion.getConnexion();
		Statement stmt = null;

		try {
			stmt = cn.createStatement();
			stmt.executeUpdate("DELETE FROM COMPUTER WHERE ID=" + pIdComputer);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(null, stmt, cn);
		}
	}

	public void updateComputer(Computer pComputer) {
		Computer prec = this.getComputerById(pComputer.getIdComputer());

		StringBuilder req = new StringBuilder("UPDATE COMPUTER SET  ");

		if (!prec.getNameComputer().equals(pComputer.getNameComputer())
				&& !pComputer.equals(""))
			req.append(" NAME=?,");
		if (prec.getIdComputer() != pComputer.getIdComputer())
			req.append(" IDCOMPANY=?,");
		if (pComputer.getIntroducedDate() != prec.getIntroducedDate()
				&& pComputer.getIntroducedDate() == null)
			req.append(" INTRODUCED=null,");
		else if (pComputer.getIntroducedDate() != prec.getIntroducedDate()
				&& pComputer.getIntroducedDate() != null)
			req.append(" INTRODUCED=?,");
		if (pComputer.getDscountedDate() != prec.getDscountedDate()
				&& pComputer.getDscountedDate() == null)
			req.append(" DISCONTINUED=null,");
		else if (pComputer.getDscountedDate() != prec.getDscountedDate()
				&& pComputer.getDscountedDate() != null)
			req.append(" DISCONTINUED=?,");

		req.deleteCharAt(req.length() - 1);
		req.append(" WHERE ID=?");

		int incr = 1;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = Connexion.getConnexion();
		try {
			stmt = cn.prepareStatement(new String(req));

			if (!prec.getNameComputer().equals(pComputer.getNameComputer())
					&& !pComputer.equals("")) {
				stmt.setString(incr, pComputer.getNameComputer());
				incr++;
			}

			if (prec.getIdComputer() != pComputer.getIdComputer()) {
				stmt.setInt(incr, pComputer.getCompany().getIdCompany());
				incr++;
			}

			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			if (pComputer.getIntroducedDate() != prec.getIntroducedDate()
					&& pComputer.getIntroducedDate() != null) {
				// Date d = (Date) pComputer.getIntroducedDate();
				stmt.setDate(incr, java.sql.Date.valueOf(formatter
						.format(pComputer.getIntroducedDate())));
				incr++;
			}

			if (pComputer.getDscountedDate() != prec.getDscountedDate()
					&& pComputer.getDscountedDate() != null) {
				// System.out.println(java.sql.Date.valueOf(formatter.format(pComputer.getDscountedDate())));
				stmt.setDate(incr, java.sql.Date.valueOf(formatter
						.format(pComputer.getDscountedDate())));
				incr++;
			}
			stmt.setInt(incr, pComputer.getIdComputer());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs, stmt, cn);
		}
	}

	public List<Computer> getComputers(String parameter,int i,double s) {
		parameter = parameter.toUpperCase();
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = Connexion.getConnexion();
		List<Computer> liste = new ArrayList<Computer>();
		try {
			StringBuilder req = new StringBuilder("SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY " +
					" WHERE UPPER(c.NAME) LIKE ? OR UPPER(d.NAMECOMPANY) LIKE ?  ORDER BY ");
			int sprime = (int) s;
			switch(sprime){
				case 1:
					req.append("UPPER(c.NAME)");
					break;
				case 2:
					req.append("c.INTRODUCED");
					break;
				case 3:
					req.append("c.DISCONTINUED");
					break;
				case 4:
					req.append("UPPER(d.NAMECOMPANY)");
					break;					
				default:
					req.append("UPPER(c.NAME)");
			}
			if(s<0){
				req.append(" DESC ");
			}else{
				req.append(" ASC ");
			}
			
			req.append(" LIMIT ?,?;");
			stmt = cn.prepareStatement(new String(req));
			stmt.setString(1, "%"+parameter+"%");
			stmt.setString(2, "%"+parameter+"%");
			stmt.setInt(3, i);
			stmt.setInt(4, NB_EL_PAGE);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Computer c = new Computer();
				c.setCompany(new Company(rs.getInt(5), rs.getString(6)));
				c.setIdComputer(rs.getInt(1));
				c.setNameComputer(rs.getString(2));
				c.setIntroducedDate(rs.getDate(3));
				c.setDscountedDate(rs.getDate(4));
				liste.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs, stmt, cn);
		}
		return liste;
	}

	public List<Computer> getComputers(int i,double s) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = Connexion.getConnexion();
		List<Computer> liste = new ArrayList<Computer>();
		try {
			
			StringBuilder req = new StringBuilder("SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY " +
					" ORDER BY ");
			int sprime = (int) s;
			switch(sprime){
				case 1:
					req.append("UPPER(c.NAME)");
					break;
				case 2:
					req.append("c.INTRODUCED");
					break;
				case 3:
					req.append("c.DISCONTINUED");
					break;
				case 4:
					req.append("UPPER(d.NAMECOMPANY)");
					break;					
				default:
					req.append("UPPER(c.NAME)");
			}
			if(s<0){
				req.append(" DESC ");
			}else{
				req.append(" ASC ");
			}
			
			req.append(" LIMIT ?,?;");
			stmt = cn.prepareStatement(new String(req));			
			stmt.setInt(1, i);
			stmt.setInt(2, NB_EL_PAGE);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Computer c = new Computer();
				c.setCompany(new Company(rs.getInt(5), rs.getString(6)));
				c.setIdComputer(rs.getInt(1));
				c.setNameComputer(rs.getString(2));
				c.setIntroducedDate(rs.getDate(3));
				c.setDscountedDate(rs.getDate(4));
				liste.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs, stmt, cn);
		}
		return liste;
	}

	public int getNbPages(String parameter) {
		parameter = parameter.toUpperCase();
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = Connexion.getConnexion();
		int nbValues = 0;
		try {
			String rq = "SELECT c.ID FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY " +
							"WHERE UPPER(c.NAME) LIKE ? OR UPPER(d.NAMECOMPANY) LIKE ? ORDER BY UPPER(c.NAME) ASC;";
			
			stmt = cn.prepareStatement(rq);
			stmt.setString(1, "%"+parameter+"%");
			stmt.setString(2, "%"+parameter+"%");
			rs = stmt.executeQuery();
			rs.last();
			nbValues = rs.getRow();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connexion.closeAll(rs, stmt, cn);
		}
		return nbValues;
	}

}
