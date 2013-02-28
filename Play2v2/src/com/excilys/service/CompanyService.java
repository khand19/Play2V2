package com.excilys.service;

import java.util.List;

import com.excilys.bean.Company;
import com.excilys.dao.CompanyDAO;
import com.excilys.dao.DAOFactory;

public enum CompanyService implements ICompanyService{
	INSTANCE;
	private CompanyDAO companyDao = DAOFactory.INSTANCE.getCompanyDAO();
	
	@Override
	public List<Company> getCompany() {
		return companyDao.getCompany();
	}

	@Override
	public Company getCompanyByID(int i) {
		return companyDao.getCompanyByID(i);
	}
}
