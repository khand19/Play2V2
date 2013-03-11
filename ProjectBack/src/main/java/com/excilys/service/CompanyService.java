package com.excilys.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.bean.Company;
import com.excilys.repository.CompanyRepository;

@Service
@Transactional(readOnly = true)
public class CompanyService implements ICompanyService{
	
	@Autowired
	private CompanyRepository repo;	
	
	public List<Company> getCompany() {
		return repo.findAll();
	}

	public Company getCompanyByID(int i) {
		return repo.findOne(i);
	} 
}
