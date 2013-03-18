package com.excilys.service;

import org.springframework.data.domain.Pageable;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;

public interface IComputerService {
	public void addComputer(Computer pComputer);
	
	public Computer getComputerById(int pIdComputer);
	
	public void deleteComputer(int pIdComputer);
	
	public void updateComputer(Computer pComputer);
	
	public ListComputer getComputers(String parameter,String searchC, Pageable page2);

	public ListComputer getComputers(Pageable page2);
	
	public boolean existComputer(int pIdComputer);
}
