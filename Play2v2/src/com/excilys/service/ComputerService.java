package com.excilys.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.excilys.bean.Computer;
import com.excilys.bean.Log;
import com.excilys.dao.ComputerDAO;
import com.excilys.dao.DAOFactory;
import com.excilys.dao.DataSourceFactory;
import com.excilys.dao.LogDAO;

public enum ComputerService implements IComputerService {
	INSTANCE;
	private ComputerDAO cDao = DAOFactory.INSTANCE.getComputerDAO();
	private LogDAO logDao = DAOFactory.INSTANCE.getLogDAO();


	@Override
	public void addComputer(Computer pComputer) {
		Connection c = DataSourceFactory.INSTANCE.getConnexion();
		try {
			c.setAutoCommit(false);
			Log l = new Log();
			cDao.addComputer(pComputer, c);
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("CREATE");
			l.setComputerLog(pComputer.toString());
			logDao.addLog(l, c);
			c.commit();
			c.setAutoCommit(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
		Connection c = DataSourceFactory.INSTANCE.getConnexion();
		try {
			c.setAutoCommit(false);
			
			Log l = new Log();
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("Delete");
			l.setComputerLog(this.getComputerById(pIdComputer).toString());	
			cDao.deleteComputer(pIdComputer,c);
			logDao.addLog(l,c);
			
			c.commit();
			c.setAutoCommit(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateComputer(Computer pComputer) {
		Connection c = DataSourceFactory.INSTANCE.getConnexion();
		try {
			c.setAutoCommit(false);
			
			Log l = new Log();
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("Uptade");
			l.setComputerLog(pComputer.toString());
			cDao.updateComputer(pComputer,c);
			logDao.addLog(l,c);
			
			c.commit();
			c.setAutoCommit(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Computer> getComputers(String parameter, int i, double s) {
		return cDao.getComputers(parameter, i, s);
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
