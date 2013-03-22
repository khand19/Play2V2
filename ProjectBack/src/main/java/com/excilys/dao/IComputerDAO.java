package com.excilys.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;

public interface IComputerDAO {

	public void addComputer(Computer pComputer);
	
	public List<Computer> getComputers();
	
	public Computer getComputerById(int pIdComputer);
	
	public void deleteComputer(int pIdComputer);
	
	public void updateComputer(Computer pComputer);
	
	public ListComputer getComputers(String parameter,String searchC, Pageable page2);

	public ListComputer getComputers(Pageable page2);
	
	public boolean existComputer(int pIdComputer);

	public List<Computer> getComputers(String filtre, String companyFiltre);
}
