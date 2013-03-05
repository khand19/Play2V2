package com.excilys.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
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

	// @Autowired
	// private JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionFactory session;

	@Override
	public void addComputer(Computer pComputer) {
		session.getCurrentSession().save(pComputer);
		// Object[] o = {
		// pComputer.getNameComputer(),pComputer.getIntroducedDate(),
		// pComputer.getDscountedDate(),pComputer.getCompany().getIdCompany() };
		// if(pComputer.getCompany().getIdCompany()==0){
		// o = new Object[] {
		// pComputer.getNameComputer(),pComputer.getIntroducedDate(),
		// pComputer.getDscountedDate(),null };
		// }
		// jdbcTemplate.update(
		// "INSERT INTO COMPUTER (NAME,INTRODUCED,DISCONTINUED,IDCOMPANY) VALUES(?,?,?,?)",o);
	}

	@Override
	public List<Computer> getComputers() {
		Query query = session
				.getCurrentSession()
				.createQuery(
						"FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY ORDER BY UPPER(c.NAME) ASC");
		return (List<Computer>) query.list();
		// return jdbcTemplate
		// .query("SELECT c.ID, c.NAME, c.INTRODUCED, c.DISCONTINUED, c.IDCOMPANY, d.NAMECOMPANY FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY ORDER BY UPPER(c.NAME) ASC;",
		// new ComputerRowMapper());
		// return null;
	}

	@Override
	public Computer getComputerById(int pIdComputer) {
		return (Computer) session.getCurrentSession().get(Computer.class,
				pIdComputer);

		// return (Computer) jdbcTemplate.queryForObject(SELECT_ID, new Object[]
		// {pIdComputer},new ComputerRowMapper());
		// return null;
	}

	@Override
	public void deleteComputer(int pIdComputer) {
		session.getCurrentSession().delete(getComputerById(pIdComputer));
		// jdbcTemplate.update(DELETE,
		// new Object[] { pIdComputer});
	}

	@Override
	public void updateComputer(Computer pComputer) {
		session.getCurrentSession().update(pComputer);
		// Object[] o = {
		// pComputer.getNameComputer(),pComputer.getIntroducedDate(),
		// pComputer.getDscountedDate(),pComputer.getCompany().getIdCompany(),pComputer.getIdComputer()};
		// if(pComputer.getCompany().getIdCompany()==0){
		// o = new Object[] {
		// pComputer.getNameComputer(),pComputer.getIntroducedDate(),
		// pComputer.getDscountedDate(),null,pComputer.getIdComputer() };
		// }
		// jdbcTemplate.update(UPDATE,o);
	}

	@Override
	public List<Computer> getComputers(String parameter, int i, double s) {
		String name = "";
		try {
			int sprime = (int) s;
			switch (sprime) {
			case 1:
				name = "nameComputer";
				break;
			case 2:
				name = "introducedDate";
				break;
			case 3:
				name = "dscountedDate";
				break;
			case 4:
				name = "cy.nameCompany";
				break;
			default:
				name = "nameComputer";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// String name;
		Order o = Order.asc(name);
		if (s < 0) {
			o = Order.desc(name);
		}		
		StringBuilder param = new StringBuilder("%"+parameter+"%");
		return session.getCurrentSession().createCriteria(Computer.class)
				.addOrder(o.ignoreCase()).add(
						Restrictions.or(Restrictions.like("nameComputer", param).ignoreCase(),Restrictions.like("nameComputer", param).ignoreCase()))
				.createAlias("company", "cy", JoinType.LEFT_OUTER_JOIN)
				.setFirstResult(i)
				.setMaxResults(NB_EL_PAGE).list();	}

	@Override
	public List<Computer> getComputers(int i, double s) {
		String name = "";
		try {
			int sprime = (int) s;
			if(sprime<0)
				sprime *= -1;
			
			switch (sprime) {
			case 1:
				name = "nameComputer";
				break;
			case 2:
				name = "introducedDate";
				break;
			case 3:
				name = "dscountedDate";
				break;
			case 4:
				name = "cy.nameCompany";
				break;
			default:
				name = "nameComputer";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// String name;
		Order o = Order.asc(name);
		if (s < 0) {
			o = Order.desc(name);
		}

		return session.getCurrentSession().createCriteria(Computer.class)
				.addOrder(o.ignoreCase()).setFirstResult(i)
				.createAlias("company", "cy", JoinType.LEFT_OUTER_JOIN)
				.setMaxResults(NB_EL_PAGE)
				.setFirstResult(i)
				.list();
	}

	@Override
	public int getNbPages(String parameter) {
		// parameter = parameter.toUpperCase();

		// String rq = "SELECT count(c.ID) FROM COMPUTER c "
		// + "WHERE UPPER(c.NAME) LIKE ?";
		// String rq =
		// "SELECT count(c.ID) FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY "
		// + "WHERE UPPER(c.NAME) LIKE ? OR UPPER(d.NAMECOMPANY) LIKE ?";
		// String rq =
		// "SELECT count(c.ID) FROM COMPUTER c LEFT JOIN COMPANY d ON c.IDCOMPANY=d.IDCOMPANY  WHERE UPPER(c.NAME) LIKE ? OR UPPER(d.NAMECOMPANY) LIKE ?";
		// return jdbcTemplate.queryForInt(rq, new Object[] { "%" + parameter +
		// "%","%" + parameter + "%"});
//		return session.getCurrentSession().get
		
		StringBuilder param = new StringBuilder("%"+parameter+"%");
		return ((Long) session.getCurrentSession()
                .createCriteria(Computer.class)
                .add(Restrictions.or(Restrictions.like("nameComputer", param).ignoreCase(),Restrictions.like("nameComputer", param).ignoreCase()))
                .setProjection(Projections.rowCount()).uniqueResult())
                .intValue();
	}
}
