package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.excilys.bean.Company;
import com.excilys.bean.Computer;

public class ComputerResultSetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException {
		Computer c = new Computer();
		c.setCompany(new Company(rs.getInt(5), rs.getString(6)));
		c.setIdComputer(rs.getInt(1));
		c.setNameComputer(rs.getString(2));
		c.setIntroducedDate(rs.getDate(3));
		c.setDscountedDate(rs.getDate(4));
		return c;
	}

}