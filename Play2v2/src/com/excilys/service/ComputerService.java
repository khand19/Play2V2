package com.excilys.service;

import java.util.List;

import com.excilys.bean.Computer;
import com.excilys.dao.ComputerDAO;

public class ComputerService implements IComputerService{
	private ComputerDAO cDao;
	
	public ComputerService(){
		cDao = new ComputerDAO();
	}
	
	@Override
	public void addComputer(Computer pComputer) {
		cDao.addComputer(pComputer);		
	}

	@Override
	public List<Computer> getComputers() {		
		return cDao.getComputers();
	}

	@Override
	public Computer getComputerById(int pIdComputer) {
		return cDao.getComputerById(pIdComputer);
	}

	@Override
	public Computer getComputerByName(String pNameComputer) {
		return cDao.getComputerByName(pNameComputer);
	}

	@Override
	public void deleteComputer(int pIdComputer) {
		cDao.deleteComputer(pIdComputer);
	}

	@Override
	public void updateComputer(Computer pComputer) {
		cDao.updateComputer(pComputer);
	}

	@Override
	public List<Computer> getComputers(String parameter, int i, double s) {
		return cDao.getComputers(parameter,i,s);
	}

	@Override
	public List<Computer> getComputers(int i, double s) {
		return cDao.getComputers(i, s);
	}

	@Override
	public int getNbPages(String parameter) {
		return cDao.getNbPages(parameter);
	}

}
