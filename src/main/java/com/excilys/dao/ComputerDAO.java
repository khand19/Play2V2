package com.excilys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import com.excilys.bean.Computer;
import com.excilys.repository.ComputerRepository;

@Repository
public class ComputerDAO implements IComputerDAO {
	private final int NB_EL_PAGE = 10;

	@Autowired
	private ComputerRepository repo;

	@Override
	public void addComputer(Computer pComputer) {
		repo.save(pComputer);
	}

	@Override
	public List<Computer> getComputers() {
		return repo.findAll();
	}

	@Override
	public Computer getComputerById(int pIdComputer) {
		return repo.findOne(pIdComputer);
	}

	@Override
	public void deleteComputer(int pIdComputer) {
		repo.delete(pIdComputer);
	}

	@Override
	public void updateComputer(Computer pComputer) {
		repo.save(pComputer);
	}

	@Override
	public Page<Computer> getComputers(String parameter, int i, double s) {
		String name = orderByName(s);
		Order o = orderByOrder(s, name);
		i/=10;
		Pageable page2 = new PageRequest(
				  i, NB_EL_PAGE, new Sort(o)
				);
		return repo.findAllByNameComputerLikeIgnoringCase("%"+parameter+"%",page2);
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

	@Override
	public Page<Computer> getComputers(int i, double s) {
		String name = orderByName(s);
		Order o = orderByOrder(s, name);		
		i/=10;
		Pageable page2 = new PageRequest(
				  i, NB_EL_PAGE, new Sort(o)
				);
		return repo.findAll(page2);
//		return repo.findAllOrderByNameComputerAsc(page2);
	}
}
