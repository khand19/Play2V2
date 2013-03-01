package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.excilys.bean.Company;
import com.excilys.dao.CompanyDAO;

@Service
public class CompanyService implements ICompanyService{
	
	@Autowired
	private CompanyDAO companyDao;
	
	@Override
	public List<Company> getCompany() {
		return companyDao.getCompany();
	}

	@Override
	public Company getCompanyByID(int i) {
		return companyDao.getCompanyByID(i);
	}
}
