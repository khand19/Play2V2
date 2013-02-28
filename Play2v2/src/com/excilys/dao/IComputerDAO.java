package com.excilys.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.bean.Computer;

public interface IComputerDAO {

	public void addComputer(Computer pComputer);
	
	public List<Computer> getComputers();
	
	public Computer getComputerById(int pIdComputer);
	
	public Computer getComputerByName(String pNameComputer);
	
	public void deleteComputer(int pIdComputer);
	
	public void updateComputer(Computer pComputer);
	
	public List<Computer> getComputers(String parameter,int i,double s);
	
	public List<Computer> getComputers(int i,double s);
	
	public int getNbPages(String parameter);
}
