package com.excilys.dao;

import java.util.List;

import com.excilys.bean.Computer;

public interface IComputerDAO {

	public void addComputer(Computer pComputer);
	
	public List<Computer> getComputers();
	
	public Computer getComputerById(int pIdComputer);
	
	public Computer getComputerByName(String pNameComputer);
	
	public void deleteComputer(int pIdComputer);
}
