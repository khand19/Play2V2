package com.excilys.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.bean.Company;

@Repository
public class CompanyDAO implements ICompanyDAO {
	
	@Autowired
	private SessionFactory session;	

	@Override
	public List<Company> getCompany() {
//		return jdbcTemplate
//				.query("SELECT IDCOMPANY, NAMECOMPANY FROM COMPANY ORDER BY NAMECOMPANY ASC;",
//						new CompanyRowMapper());
//		int i = 5;
//		Company c = (Company)session.getCurrentSession().get(Company.class, i);
//		System.out.println(c);
//		return null;
//		System.out.println(session.toString());
//		System.out.println(session.getCurrentSession().toString());

		Query query=session.getCurrentSession().createQuery("FROM Company ORDER BY NAMECOMPANY ASC");		
		return (List<Company>) query.list();
	}

	@Override
	public Company getCompanyByID(int i) {
//		return (Company) jdbcTemplate
//				.queryForObject("SELECT IDCOMPANY, NAMECOMPANY FROM COMPANY WHERE IDCOMPANY=? ORDER BY NAMECOMPANY ASC",
//						new Object[] { i }, new CompanyRowMapper());
		return (Company)session.getCurrentSession().get(Company.class, i);
	}

}
