package com.excilys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.bean.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer>{

//	public List<Company> findAllOrderByNameCompany();
}
