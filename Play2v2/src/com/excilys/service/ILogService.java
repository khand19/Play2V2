package com.excilys.service;

import java.util.List;

import com.excilys.bean.Log;

public interface ILogService {

	public List<Log> getLog();
	
	public void addLog(Log l);
}
