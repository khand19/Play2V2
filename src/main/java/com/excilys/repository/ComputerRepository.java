package com.excilys.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.bean.Computer;

public interface ComputerRepository  extends JpaRepository<Computer,Integer>{

	Page<Computer> findAllByNameComputerLikeIgnoringCase(String parameter,Pageable page2);

//	Page<Computer> findAllOrderByNameComputerAsc(Pageable page2);

}
