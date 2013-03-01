package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.excilys.bean.Company;
import com.excilys.bean.Computer;

public enum ComputerDAO implements IComputerDAO {
	INSTANCE;
	private static final String SELECT_ORDER = "SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY ORDER BY ";
	private static final String SELECT_LIKE_ORDER = "SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY WHERE UPPER(c.NAME) LIKE ? OR UPPER(d.NAMECOMPANY) LIKE ?  ORDER BY ";
	private static final String DELETE = "DELETE FROM COMPUTER WHERE ID=";
	private static final String SELECT_ID = "SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY WHERE c.ID=";
	private static final String SELECT_ALL = "SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY ORDER BY UPPER(c.NAME) ASC;";
	private final int NB_EL_PAGE = 10;

	@Override
	public void addComputer(Computer pComputer) {
		Connection c = DataSourceFactory.INSTANCE.getThreadConnexion();
		StringBuilder req = new StringBuilder("INSERT INTO COMPUTER SET ");
		if (!pComputer.equals(""))
			req.append(" NAME=?,");
		if (pComputer.getCompany().getIdCompany() != 0)
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
		try {
			stmt = c.prepareStatement(new String(req));

			if (!pComputer.equals("")) {
				stmt.setString(incr, pComputer.getNameComputer());
				incr++;
			}
			if (pComputer.getCompany().getIdCompany() != 0) {
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
			rs = stmt.getGeneratedKeys();
			rs.next();
			pComputer.setIdComputer(rs.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Computer> getComputers() {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = DataSourceFactory.INSTANCE.getThreadConnexion();
		List<Computer> liste = new ArrayList<Computer>();
		try {
			stmt = cn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
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
	public Computer getComputerById(int pIdComputer) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection cn = DataSourceFactory.INSTANCE.getThreadConnexion();
		Computer c = new Computer();
		try {
			stmt = cn.createStatement();
			rs = stmt.executeQuery(SELECT_ID + pIdComputer);
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

	@Override
	public void deleteComputer(int pIdComputer) {
		Connection c = DataSourceFactory.INSTANCE.getThreadConnexion();
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(DELETE + pIdComputer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateComputer(Computer pComputer) {
		Computer prec = this.getComputerById(pComputer.getIdComputer());
		Connection c = DataSourceFactory.INSTANCE.getThreadConnexion();


		StringBuilder req = new StringBuilder("UPDATE COMPUTER SET  ");

		int i = 1;

		if (!prec.getNameComputer().equals(pComputer.getNameComputer())
				&& !pComputer.equals("")) {
			req.append(" NAME=?,");
			i++;
		}
		if (prec.getCompany().getIdCompany() != pComputer.getCompany().getIdCompany()) {
			req.append(" IDCOMPANY=?,");
			i++;
		}
		if (pComputer.getIntroducedDate() != prec.getIntroducedDate()
				&& pComputer.getIntroducedDate() == null) {
			req.append(" INTRODUCED=null,");
			i++;
		} else if (pComputer.getIntroducedDate() != prec.getIntroducedDate()
				&& pComputer.getIntroducedDate() != null) {
			req.append(" INTRODUCED=?,");
			i++;
		}
		if (pComputer.getDscountedDate() != prec.getDscountedDate()
				&& pComputer.getDscountedDate() == null) {
			req.append(" DISCONTINUED=null,");
			i++;
		} else if (pComputer.getDscountedDate() != prec.getDscountedDate()
				&& pComputer.getDscountedDate() != null) {
			req.append(" DISCONTINUED=?,");
			i++;
		}

		if (i != 1) {
			req.deleteCharAt(req.length() - 1);
			req.append(" WHERE ID=?");

			int incr = 1;
			PreparedStatement stmt = null;
			try {
				stmt = c.prepareStatement(new String(req));

				if (!prec.getNameComputer().equals(pComputer.getNameComputer())
						&& !pComputer.equals("")) {
					stmt.setString(incr, pComputer.getNameComputer());
					incr++;
				}

				if (prec.getCompany().getIdCompany() != pComputer.getCompany().getIdCompany()) {
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
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Computer> getComputers(String parameter, int i, double s) {
		parameter = parameter.toUpperCase();

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = DataSourceFactory.INSTANCE.getThreadConnexion();
		List<Computer> liste = new ArrayList<Computer>();
		try {
			StringBuilder req = new StringBuilder(SELECT_LIKE_ORDER);
			int sprime = (int) s;
			switch (sprime) {
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
			if (s < 0) {
				req.append(" DESC ");
			} else {
				req.append(" ASC ");
			}

			req.append(" LIMIT ?,?;");
			stmt = cn.prepareStatement(new String(req));
			stmt.setString(1, "%" + parameter + "%");
			stmt.setString(2, "%" + parameter + "%");
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
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return liste;
	}

	public List<Computer> getComputers(int i, double s) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = DataSourceFactory.INSTANCE.getThreadConnexion();
		List<Computer> liste = new ArrayList<Computer>();
		try {

			StringBuilder req = new StringBuilder(SELECT_ORDER);
			int sprime = (int) s;
			switch (sprime) {
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
			if (s < 0) {
				req.append(" DESC ");
			} else {
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
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return liste;
	}

	public int getNbPages(String parameter) {
		parameter = parameter.toUpperCase();

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection cn = DataSourceFactory.INSTANCE.getThreadConnexion();
		int nbValues = 0;
		try {
			String rq = "SELECT c.ID FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY "
					+ "WHERE UPPER(c.NAME) LIKE ? OR UPPER(d.NAMECOMPANY) LIKE ? ORDER BY UPPER(c.NAME) ASC;";

			stmt = cn.prepareStatement(rq);
			stmt.setString(1, "%" + parameter + "%");
			stmt.setString(2, "%" + parameter + "%");
			rs = stmt.executeQuery();
			rs.last();
			nbValues = rs.getRow();
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
		return nbValues;
	}

}
