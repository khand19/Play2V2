package com.excilys.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;
import com.excilys.bean.Log;
import com.excilys.dao.ComputerDAO;
import com.excilys.dao.DataSourceFactory;
import com.excilys.dao.LogDAO;

@Service
public class ComputerService implements IComputerService {

	@Autowired
	private ComputerDAO cDao;
	@Autowired
	private LogDAO logDao;


	@Override
	public void addComputer(Computer pComputer) {
		Connection c = DataSourceFactory.INSTANCE.getThreadConnexion();
		try {
			c.setAutoCommit(false);
			Log l = new Log();
			cDao.addComputer(pComputer);
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("CREATE");
			l.setComputerLog(pComputer.toString());
			logDao.addLog(l);
			c.commit();
			c.setAutoCommit(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DataSourceFactory.INSTANCE.closeConnexion();
		}
	}

//	@Override
//	public List<Computer> getComputers() {
//		return cDao.getComputers();
//	}

	@Override
	public Computer getComputerById(int pIdComputer) {
		Computer c = cDao.getComputerById(pIdComputer);
		return c;
	}

	@Override
	public void deleteComputer(int pIdComputer) {
		Connection c = DataSourceFactory.INSTANCE.getThreadConnexion();
		try {
			c.setAutoCommit(false);
			
			Log l = new Log();
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("Delete");
			l.setComputerLog(this.getComputerById(pIdComputer).toString());	
			cDao.deleteComputer(pIdComputer);
			logDao.addLog(l);
			c.commit();
			c.setAutoCommit(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DataSourceFactory.INSTANCE.closeConnexion();
		}
	}

	@Override
	public void updateComputer(Computer pComputer) {
		Connection c = DataSourceFactory.INSTANCE.getThreadConnexion();

		try {
			c.setAutoCommit(false);
			
			Log l = new Log();
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("Uptade");
			l.setComputerLog(pComputer.toString());
			cDao.updateComputer(pComputer);
			logDao.addLog(l);
			
			c.commit();
			c.setAutoCommit(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DataSourceFactory.INSTANCE.closeConnexion();
		}
	}

	@Override
	public ListComputer getComputers(String parameter, int i, double s) {
		ListComputer l = new ListComputer(cDao.getComputers(parameter,i,s),cDao.getNbPages(parameter));
		DataSourceFactory.INSTANCE.closeConnexion();
		return l;
	}


	@Override
	public ListComputer getComputers(int i, double s) {
		ListComputer l = new ListComputer(cDao.getComputers(i,s),cDao.getNbPages(""));
		DataSourceFactory.INSTANCE.closeConnexion();
		return l;
	}

	@Override
	public int getNbPages(String parameter) {
		return cDao.getNbPages(parameter);
	}

}
