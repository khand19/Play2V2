package com.excilys.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.bean.Company;
import com.excilys.dao.ICompanyDAO;

@Service
@Transactional(readOnly = true)
public class CompanyService implements ICompanyService{
	
	@Autowired
	private ICompanyDAO companyDao;
	
	@Override
	public List<Company> getCompany() {
		return companyDao.getCompany();
	}

	@Override
	public Company getCompanyByID(int i) {
		return companyDao.getCompanyByID(i);
	}
}
