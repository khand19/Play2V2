package com.excilys.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;
import com.excilys.bean.QComputer;
import com.excilys.repository.ComputerRepository;
import com.mysema.query.BooleanBuilder;

@Repository
public class ComputerDAO implements IComputerDAO {
	private final int NB_EL_PAGE = 10;

	@PersistenceContext
	private EntityManager ent;

	@Autowired
	private ComputerRepository repo;

	public void addComputer(Computer pComputer) {
		repo.save(pComputer);
	}

	public List<Computer> getComputers() {
		return repo.findAll();
	}

	public Computer getComputerById(int pIdComputer) {
		return repo.findOne(pIdComputer);
	}

	public void deleteComputer(int pIdComputer) {
		repo.delete(pIdComputer);
	}

	public void updateComputer(Computer pComputer) {
		repo.save(pComputer);
	}

	public ListComputer getComputers(String parameter, String searchC, int i,
			double s) {
		String name = orderByName(s);
		Order o = orderByOrder(s, name);
		i /= 10;
		Pageable page2 = new PageRequest(i, NB_EL_PAGE, new Sort(o));

		BooleanBuilder bb = new BooleanBuilder();
		if (searchC != null) {
			// QComputer.computer.company
			bb.and(QComputer.computer.company.nameCompany
					.containsIgnoreCase(searchC));
		}
		if (parameter != null) {
			bb.and(QComputer.computer.nameComputer
					.containsIgnoreCase(parameter));
		}
		Page<Computer> pc2 = repo.findAll(bb, page2);
		ListComputer l = new ListComputer(pc2.getContent(),
				(int) pc2.getTotalElements());

		return l;
	}

	private Order orderByOrder(double s, String name) {
		// String name;
		Order o = new Order(Direction.ASC, name);
		if (s < 0) {
			o = new Order(Direction.DESC, name);
		}
		return o;
	}

	private String orderByName(double s) {
		String name = "";
		try {
			int sprime = (int) s;
			if (s < 0)
				sprime *= -1;
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

	public ListComputer getComputers(int i, double s) {
		String name = orderByName(s);
		Order o = orderByOrder(s, name);
		i /= 10;
		Pageable page2 = new PageRequest(i, NB_EL_PAGE, new Sort(o));
		Page<Computer> pc = repo.findAll(page2);
		ListComputer l = new ListComputer(pc.getContent(),
				(int) pc.getTotalElements());
		return l;
		// return repo.findAll(page2);
		// return repo.findAllOrderByNameComputerAsc(page2);
	}

	public boolean existComputer(int pIdComputer) {
		return repo.exists(pIdComputer);
	}
}