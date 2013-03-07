package com.excilys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.bean.Company;
import com.excilys.repository.CompanyRepository;

@Repository
public class CompanyDAO implements ICompanyDAO {
	
	@Autowired
	private CompanyRepository repo;	

	public List<Company> getCompany() {
		return repo.findAll();
	}

	public Company getCompanyByID(int i) {
		return repo.findOne(i);
	}

}
