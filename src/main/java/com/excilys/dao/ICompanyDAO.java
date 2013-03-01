package com.excilys.dao;

import java.util.List;

import com.excilys.bean.Company;

public interface ICompanyDAO {
	public List<Company> getCompany();
	
	public Company getCompanyByID(int i);
	
}
