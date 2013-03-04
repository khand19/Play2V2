package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.bean.Log;
import com.excilys.dao.LogDAO;

@Service
public class LogService implements ILogService{
	
	@Autowired
	private LogDAO logDao;
		
	@Override
	public List<Log> getLog() {
		return logDao.getLog();
	}

	@Override
	public void addLog(Log l) {
		logDao.addLog(l);
	}

}
