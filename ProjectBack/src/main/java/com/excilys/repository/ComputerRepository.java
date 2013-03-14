package com.excilys.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.excilys.bean.Computer;

public interface ComputerRepository  extends JpaRepository<Computer,Integer>,QueryDslPredicateExecutor<Computer>{

	Page<Computer> findAllByNameComputerContainingIgnoringCase(String parameter,Pageable page2);

//	Page<Computer> findAllOrderByNameComputerAsc(Pageable page2);
//	Page<Computer> findAll(Predicat p);
	
}
