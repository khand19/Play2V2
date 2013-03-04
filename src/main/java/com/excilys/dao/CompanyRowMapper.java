package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CompanyRowMapper implements RowMapper {

	@Override
	  public Object mapRow(ResultSet rs, int line) throws SQLException {
	    CompanyResultSetExtractor extractor = new CompanyResultSetExtractor();
	    return extractor.extractData(rs);
	}

}
