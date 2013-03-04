package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.excilys.bean.Company;

public class CompanyResultSetExtractor  implements ResultSetExtractor {

	  @Override
	  public Object extractData(ResultSet rs) throws SQLException {
	    Company company = new Company();
	    company.setIdCompany(rs.getInt(1));
	    company.setNameCompany(rs.getString(2));
	    return company;
	  }

	} 
