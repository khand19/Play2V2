package com.excilys.service;

import java.util.List;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;

public interface IComputerService {
	public void addComputer(Computer pComputer);
	
	public List<Computer> getComputers();
	
	public Computer getComputerById(int pIdComputer);
	
	public Computer getComputerByName(String pNameComputer);
	
	public void deleteComputer(int pIdComputer);
	
	public void updateComputer(Computer pComputer);
	
	public ListComputer getComputers(String parameter,int i,double s);
	
	public ListComputer getComputers(int i,double s);
	
	public int getNbPages(String parameter);
}
