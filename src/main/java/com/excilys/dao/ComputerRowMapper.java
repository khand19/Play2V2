package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ComputerRowMapper implements RowMapper {

	@Override
	  public Object mapRow(ResultSet rs, int line) throws SQLException {
	    ComputerResultSetExtractor extractor = new ComputerResultSetExtractor();
	    return extractor.extractData(rs);
	}

}
