package com.excilys.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.excilys.bean.Computer;

@Repository
public class ComputerDAO implements IComputerDAO {
	private static final String UPDATE = "UPDATE COMPUTER SET NAME=?,INTRODUCED=?,DISCONTINUED=?,IDCOMPANY=? WHERE ID=?";
	private static final String SELECT_ORDER = "SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY ORDER BY ";
	private static final String SELECT_LIKE_ORDER = "SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY WHERE UPPER(c.NAME) LIKE ? OR UPPER(d.NAMECOMPANY) LIKE ?  ORDER BY ";
	private static final String DELETE = "DELETE FROM COMPUTER WHERE ID=?";
	private static final String SELECT_ID = "SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY WHERE c.ID=?";
	private static final String SELECT_ALL = "SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY ORDER BY UPPER(c.NAME) ASC;";
	private final int NB_EL_PAGE = 10;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addComputer(Computer pComputer) {
		jdbcTemplate.update(
				"INSERT INTO COMPUTER (NAME,INTRODUCED,DISCONTINUED,IDCOMPANY) VALUES(?,?,?,?)",
				new Object[] { pComputer.getNameComputer(),pComputer.getIntroducedDate(),
						pComputer.getDscountedDate(),pComputer.getCompany().getIdCompany() });
	}

	@Override
	public List<Computer> getComputers() {
		return jdbcTemplate
				.query("SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY ORDER BY UPPER(c.NAME) ASC;",
						new ComputerRowMapper());
	}

	@Override
	public Computer getComputerById(int pIdComputer) {
		return (Computer) jdbcTemplate.queryForObject(SELECT_ID, new Object[] {pIdComputer},new ComputerRowMapper());
	}

	@Override
	public void deleteComputer(int pIdComputer) {
		jdbcTemplate.update(DELETE,
	        new Object[] { pIdComputer});
	}

	@Override
	public void updateComputer(Computer pComputer) {
		jdbcTemplate.update(UPDATE,
				new Object[] { pComputer.getNameComputer(),pComputer.getIntroducedDate(),
						pComputer.getDscountedDate(),pComputer.getCompany().getIdCompany(),pComputer.getIdComputer() });
	}

	@Override
	public List<Computer> getComputers(String parameter, int i, double s) {
		parameter = parameter.toUpperCase();

		StringBuilder req = new StringBuilder(SELECT_LIKE_ORDER);
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jdbcTemplate.query(req.toString(), new Object[] {
				"%" + parameter + "%", "%" + parameter + "%", i, NB_EL_PAGE },
				new ComputerRowMapper());
	}

	@Override
	public List<Computer> getComputers(int i, double s) {
		StringBuilder req = new StringBuilder(SELECT_ORDER);
		try {
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jdbcTemplate.query(req.toString(), new Object[] { i, NB_EL_PAGE },
				new ComputerRowMapper());
	}

	@Override
	public int getNbPages(String parameter) {
		parameter = parameter.toUpperCase();
		
//		String rq = "SELECT count(c.ID) FROM COMPUTER c "
//				+ "WHERE UPPER(c.NAME) LIKE ?";
//		String rq = "SELECT count(c.ID) FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY "
//				+ "WHERE UPPER(c.NAME) LIKE ? OR UPPER(d.NAMECOMPANY) LIKE ?";
		String rq = "SELECT count(c.ID) FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY  WHERE UPPER(c.NAME) LIKE ? OR UPPER(d.NAMECOMPANY) LIKE ?";
		return jdbcTemplate.queryForInt(rq, new Object[] { "%" + parameter + "%","%" + parameter + "%"});
	}
}
