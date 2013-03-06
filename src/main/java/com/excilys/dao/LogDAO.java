package com.excilys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.bean.Log;
import com.excilys.repository.LogRepository;

@Repository
public class LogDAO  implements ILogDAO{
	@Autowired
	private LogRepository repo;	
	
	@Override
	public List<Log> getLog() {
		return repo.findAll();
	}

	@Override
	public void addLog(Log l) {
		repo.save(l);
	}
}
