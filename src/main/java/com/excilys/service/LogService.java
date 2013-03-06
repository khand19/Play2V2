package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.bean.Log;
import com.excilys.dao.ILogDAO;

@Service
public class LogService implements ILogService{
	
	@Autowired
	private ILogDAO logDao;
		
	@Override
	@Transactional(readOnly = true)
	public List<Log> getLog() {
		return logDao.getLog();
	}

	@Override
	@Transactional
	public void addLog(Log l) {
		logDao.addLog(l);
	}

}
