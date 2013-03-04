package com.excilys.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.excilys.bean.Company;

@Repository
public class CompanyDAO implements ICompanyDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;	

	@Override
	public List<Company> getCompany() {
		return jdbcTemplate
				.query("SELECT IDCOMPANY, NAMECOMPANY FROM COMPANY ORDER BY NAMECOMPANY ASC;",
						new CompanyRowMapper());
	}

	@Override
	public Company getCompanyByID(int i) {
		return (Company) jdbcTemplate
				.queryForObject("SELECT IDCOMPANY, NAMECOMPANY FROM COMPANY WHERE IDCOMPANY=? ORDER BY NAMECOMPANY ASC",
						new Object[] { i }, new CompanyRowMapper());
	}

}
