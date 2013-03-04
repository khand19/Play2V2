package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.bean.Log;

public class LogRowMapper implements RowMapper<Log> {

	@Override
	  public Log mapRow(ResultSet rs, int line) throws SQLException {
	    LogResultSetExtractor extractor = new LogResultSetExtractor();
	    return extractor.extractData(rs);
	}

}
