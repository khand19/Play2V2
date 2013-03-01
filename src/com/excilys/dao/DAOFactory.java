package com.excilys.dao;


public enum DAOFactory {
	INSTANCE;

	public CompanyDAO getCompanyDAO() {
		return CompanyDAO.INSTANCE;
	}
	public ComputerDAO getComputerDAO() {
		return ComputerDAO.INSTANCE;
	}
	public LogDAO getLogDAO() {
		return LogDAO.INSTANCE;
	}
}
