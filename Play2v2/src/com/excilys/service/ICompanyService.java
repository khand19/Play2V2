package com.excilys.service;

import java.util.List;

import com.excilys.bean.Company;

public interface ICompanyService {
	public List<Company> getCompany();
	
	public Company getCompanyByID(int i);	
}