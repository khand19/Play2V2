package com.excilys.service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;
import com.excilys.bean.Log;
import com.excilys.dao.IComputerDAO;
 
@Service
@Transactional(readOnly = true)
public class ComputerService implements IComputerService {
	@Autowired
	private IComputerDAO compDao;
	
	@Autowired
	private ILogService logDao;

	@Transactional(readOnly = false)
	public void addComputer(Computer pComputer) {
		Log l = new Log();

		compDao.addComputer(pComputer);
		
		Date now = Calendar.getInstance().getTime();
		l.setDateLog(now);
		l.setOptionLog("CREATE");
		l.setComputerLog(pComputer.toString());
		logDao.addLog(l);
	}
	
	public Computer getComputerById(int pIdComputer) {
		Computer c = compDao.getComputerById(pIdComputer);
		return c;
	}
	
	public boolean existComputer(int pIdComputer){
		return compDao.existComputer(pIdComputer);
	}

	@Transactional(readOnly = false)
	public void deleteComputer(int pIdComputer) {
			Log l = new Log();
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("Delete");
			l.setComputerLog(this.getComputerById(pIdComputer).toString());	
			
			compDao.deleteComputer(pIdComputer);
			
			logDao.addLog(l);
	}

	@Transactional(readOnly = false)
	public void updateComputer(Computer pComputer) {
			Log l = new Log();
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("Uptade");
			l.setComputerLog(pComputer.toString());
			
			compDao.updateComputer(pComputer);
			
			logDao.addLog(l);
	}
	
	public ListComputer getComputers(String parameter,String searchC, Pageable page2){
		ListComputer l = compDao.getComputers(parameter, searchC, page2);
		return l;
	}

	public ListComputer getComputers(Pageable page2) {
		ListComputer l = compDao.getComputers(page2);
		return l;
	}

	public List<Computer> getComputers(String filtre, String companyFiltre) {
		return compDao.getComputers(filtre, companyFiltre);
	}
}