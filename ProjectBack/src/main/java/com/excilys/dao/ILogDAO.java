package com.excilys.dao;

import java.util.List;

import com.excilys.bean.Log;

public interface ILogDAO {
	public List<Log> getLog();
	
	public void addLog(Log l);
}