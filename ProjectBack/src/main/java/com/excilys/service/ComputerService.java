package com.excilys.service;


import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;
import com.excilys.bean.Log;
import com.excilys.repository.ComputerRepository;

@Service
@Transactional(readOnly = true)
public class ComputerService implements IComputerService {
	private final int NB_EL_PAGE = 10;

	@Autowired
	private ComputerRepository repo;
	
	@Autowired
	private ILogService logDao;

	@Transactional(readOnly = false)
	public void addComputer(Computer pComputer) {
		Log l = new Log();
		
		repo.save(pComputer);
		
		Date now = Calendar.getInstance().getTime();
		l.setDateLog(now);
		l.setOptionLog("CREATE");
		l.setComputerLog(pComputer.toString());
		logDao.addLog(l);
	}

	public Computer getComputerById(int pIdComputer) {
		Computer c = repo.findOne(pIdComputer);
		return c;
	}

	@Transactional(readOnly = false)
	public void deleteComputer(int pIdComputer) {
			Log l = new Log();
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("Delete");
			l.setComputerLog(this.getComputerById(pIdComputer).toString());	
			
			repo.delete(pIdComputer);
			
			logDao.addLog(l);
	}

	@Transactional(readOnly = false)
	public void updateComputer(Computer pComputer) {
			Log l = new Log();
			Date now = Calendar.getInstance().getTime();
			l.setDateLog(now);
			l.setOptionLog("Uptade");
			l.setComputerLog(pComputer.toString());
			
			repo.save(pComputer);
			
			logDao.addLog(l);
	}

	public ListComputer getComputers(String parameter, int i, double s) {
		String name = orderByName(s);
		Order o = orderByOrder(s, name);
		i/=10;
		Pageable page2 = new PageRequest(
				  i, NB_EL_PAGE, new Sort(o)
				);
		Page<Computer> pc = repo.findAllByNameComputerContainingIgnoringCase(parameter,page2);
		
		ListComputer l = new ListComputer(pc.getContent(),(int)pc.getTotalElements());
		return l;
	}


	public ListComputer getComputers(int i, double s) {
		String name = orderByName(s);
		Order o = orderByOrder(s, name);		
		i/=10;
		Pageable page2 = new PageRequest(
				  i, NB_EL_PAGE, new Sort(o)
				);
		Page<Computer> pc = repo.findAll(page2);
		ListComputer l = new ListComputer(pc.getContent(),(int)pc.getTotalElements());
		return l;
	}
	
	private Order orderByOrder(double s, String name) {
		// String name;
		Order o =  new Order(Direction.ASC, name);
		if (s < 0) {
			o =  new Order(Direction.DESC, name);
		}
		return o;
	}

	private String orderByName(double s) {
		String name = "";
		try {
			int sprime = (int) s;
			if(s<0)
				sprime*=-1;
			switch (sprime) {
			case 1:
				name = "nameComputer";
				break;
			case 2:
				name = "introducedDate";
				break;
			case 3:
				name = "dscountedDate";
				break;
			case 4:
				name = "company.nameCompany";
				break;
			default:
				name = "nameComputer";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}
