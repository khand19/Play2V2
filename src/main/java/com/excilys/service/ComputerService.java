package com.excilys.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;
import com.excilys.bean.Log;
import com.excilys.dao.ComputerDAO;
import com.excilys.dao.LogDAO;

@Service
public class ComputerService implements IComputerService {

	@Autowired
	private ComputerDAO cDao;
	@Autowired
	private LogDAO logDao;

	@Override
	@Transactional
	public void addComputer(Computer pComputer) {
		Log l = new Log();
		cDao.addComputer(pComputer);
		Date now = Calendar.getInstance().getTime();
		l.setDateLog(now);
		l.setOptionLog("CREATE");
		l.setComputerLog(pComputer.toString());
		logDao.addLog(l);
	}

	@Override
	public Computer getComputerById(int pIdComputer) {
		Computer c = cDao.getComputerById(pIdComputer);
		return c;
	}

	@Override
	@Transactional
	public void deleteComputer(int pIdComputer) {
			Log l = new Log();
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("Delete");
			l.setComputerLog(this.getComputerById(pIdComputer).toString());	
			cDao.deleteComputer(pIdComputer);
			logDao.addLog(l);
	}

	@Override
	@Transactional
	public void updateComputer(Computer pComputer) {
			Log l = new Log();
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("Uptade");
			l.setComputerLog(pComputer.toString());
			cDao.updateComputer(pComputer);
			logDao.addLog(l);
	}

	@Override
	@Transactional
	public ListComputer getComputers(String parameter, int i, double s) {
		ListComputer l = new ListComputer(cDao.getComputers(parameter,i,s),cDao.getNbPages(parameter));
		return l;
	}


	@Override
	@Transactional
	public ListComputer getComputers(int i, double s) {
		ListComputer l = new ListComputer(cDao.getComputers(i,s),cDao.getNbPages(""));
		return l;
	}

	@Override
	@Transactional
	public int getNbPages(String parameter) {
		return cDao.getNbPages(parameter);
	}

}
