package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.bean.Log;
import com.excilys.dao.ILogDAO;

@Service
@Transactional(readOnly = true)
public class LogService implements ILogService{
	
	@Autowired
	private ILogDAO logDao;
		
	@Override
	public List<Log> getLog() {
		return logDao.getLog();
	}

	@Override
	@Transactional(readOnly = false)
	public void addLog(Log l) {
		logDao.addLog(l);
	}

}
