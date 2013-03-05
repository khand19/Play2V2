package com.excilys.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.bean.Company;
import com.excilys.bean.Log;

@Repository
public class LogDAO  implements ILogDAO{
	private static final String SELECT_ALL = "SELECT IDLOG,DATELOG,OPTIONLOG,COMPUTERLOG FROM LOG ORDER BY DATELOG ASC";
	private static final String INSERT = "INSERT INTO LOG SET DATELOG=?,OPTIONLOG=?,COMPUTERLOG=?";

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory session;	
	
	@Override
	public List<Log> getLog() {
		Query query=session.getCurrentSession().createQuery("FROM LOG ORDER BY DATELOG ASC");
		return (List<Log>) query.list();
//		return jdbcTemplate
//				.query(SELECT_ALL,
//						new LogRowMapper());
	}

	@Override
	public void addLog(Log l) {
		session.getCurrentSession().saveOrUpdate(l);
//		jdbcTemplate.update(
//				INSERT,
//				new Object[] { l.getDateLog(),l.getOptionLog(),l.getComputerLog() });		
	}
}
