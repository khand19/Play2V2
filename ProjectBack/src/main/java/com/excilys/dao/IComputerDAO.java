package com.excilys.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.excilys.bean.Computer;

public interface IComputerDAO {

	public void addComputer(Computer pComputer);
	
	public List<Computer> getComputers();
	
	public Computer getComputerById(int pIdComputer);
	
	public void deleteComputer(int pIdComputer);
	
	public void updateComputer(Computer pComputer);
	
	public Page<Computer> getComputers(String parameter,int i,double s);
	
	public Page<Computer> getComputers(int i,double s);
}
