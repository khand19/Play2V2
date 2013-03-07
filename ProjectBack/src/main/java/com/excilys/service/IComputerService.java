package com.excilys.service;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;

public interface IComputerService {
	public void addComputer(Computer pComputer);
	
	public Computer getComputerById(int pIdComputer);
	
	public void deleteComputer(int pIdComputer);
	
	public void updateComputer(Computer pComputer);
	
	public ListComputer getComputers(String parameter,int i,double s);
	
	public ListComputer getComputers(int i,double s);
}
