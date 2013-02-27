package com.excilys.service;

import java.util.List;

import com.excilys.bean.Company;
import com.excilys.dao.CompanyDAO;

public class CompanyService implements ICompanyService{
	private CompanyDAO companyDao;
	
	public CompanyService(){
		companyDao = new CompanyDAO();
	}
	
	@Override
	public List<Company> getCompany() {
		return companyDao.getCompany();
	}

	@Override
	public Company getCompanyByID(int i) {
		return companyDao.getCompanyByID(i);
	}
}
