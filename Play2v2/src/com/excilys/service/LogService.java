package com.excilys.service;

import java.util.List;

import com.excilys.bean.Log;
import com.excilys.dao.DAOFactory;
import com.excilys.dao.LogDAO;

public enum LogService implements ILogService{
	INSTANCE;
	private LogDAO logDao = DAOFactory.INSTANCE.getLogDAO();
		
	@Override
	public List<Log> getLog() {
		return logDao.getLog();
	}

	@Override
	public void addLog(Log l) {
		logDao.addLog(l);
	}

}
