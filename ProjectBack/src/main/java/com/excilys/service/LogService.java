package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.bean.Log;
import com.excilys.repository.LogRepository;
 
@Service
@Transactional(readOnly = true)
public class LogService implements ILogService{
	@Autowired
	private LogRepository repo;	
	
	public List<Log> getLog() {
		return repo.findAll();
	}

	public void addLog(Log l) {
		repo.save(l);
	}
}
