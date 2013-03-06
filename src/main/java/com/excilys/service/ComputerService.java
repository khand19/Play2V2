package com.excilys.service;


import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;
import com.excilys.bean.Log;
import com.excilys.dao.IComputerDAO;
import com.excilys.dao.ILogDAO;

@Service
public class ComputerService implements IComputerService {

	@Autowired
	private IComputerDAO cDao;
	@Autowired
	private ILogDAO logDao;

	@Override
	@Transactional
	public void addComputer(Computer pComputer) {
		Log l = new Log();
		System.out.println(pComputer);
		cDao.addComputer(pComputer);
		Date now = Calendar.getInstance().getTime();
		l.setDateLog(now);
		l.setOptionLog("CREATE");
		l.setComputerLog(pComputer.toString());
		logDao.addLog(l);
	}

	@Override
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
	public ListComputer getComputers(String parameter, int i, double s) {
		Page<Computer> pc = cDao.getComputers(parameter,i,s);
		ListComputer l = new ListComputer(pc.getContent(),(int)pc.getTotalElements());
		return l;
	}


	@Override
	@Transactional(readOnly = true)
	public ListComputer getComputers(int i, double s) {
		Page<Computer> pc = cDao.getComputers(i,s);
		ListComputer l = new ListComputer(pc.getContent(),(int)pc.getTotalElements());
		return l;
	}
}
