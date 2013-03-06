package com.excilys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.bean.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer>{

}
