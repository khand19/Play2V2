package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.excilys.bean.Log;

public class LogResultSetExtractor implements ResultSetExtractor<Log> {

	@Override
	public Log extractData(ResultSet rs) throws SQLException {
		Log l = new Log();
		l.setIdLog(rs.getInt(1));
		l.setDateLog((Date)rs.getDate(2));
		l.setOptionLog(rs.getString(3));
		l.setComputerLog(rs.getString(4));
		return l;
	}

}
