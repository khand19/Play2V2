package com.excilys.service;

import java.sql.Connection;
import java.util.List;

import com.excilys.bean.Log;
import com.excilys.dao.LogDAO;

public class LogService implements ILogService{
	private LogDAO logDao;
	
	public LogService(){
		logDao = new LogDAO();
	}
	
	@Override
	public List<Log> getLog() {
		return logDao.getLog();
	}

	@Override
	public void addLog(Log l,Connection c) {
		logDao.addLog(l,c);
	}

}
