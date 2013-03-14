package com.excilys.dao;

import java.util.List;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;

public interface IComputerDAO {

	public void addComputer(Computer pComputer);
	
	public List<Computer> getComputers();
	
	public Computer getComputerById(int pIdComputer);
	
	public void deleteComputer(int pIdComputer);
	
	public void updateComputer(Computer pComputer);
	
	public ListComputer getComputers(String parameter,String searchC, int i,double s);
	
	public ListComputer getComputers(int i,double s);
	
	public boolean existComputer(int pIdComputer);
}
