package com.excilys.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;

public interface IComputerService {
	@Secured(value = { "ROLE_ADMIN" })
	public void addComputer(Computer pComputer);

	@Secured({ "ROLE_ADMIN","ROLE_DEFAULT" })
	public Computer getComputerById(int pIdComputer);
	
	@Secured(value = { "ROLE_ADMIN" })
	public void deleteComputer(int pIdComputer);
	
	@Secured(value = { "ROLE_ADMIN" })
	public void updateComputer(Computer pComputer);

	@Secured({ "ROLE_ADMIN","ROLE_DEFAULT" })
	public ListComputer getComputers(String parameter,String searchC, Pageable page2);

	@Secured({ "ROLE_ADMIN","ROLE_DEFAULT" })
	public ListComputer getComputers(Pageable page2);

	@Secured({ "ROLE_ADMIN","ROLE_DEFAULT" })
	public boolean existComputer(int pIdComputer);

	public List<Computer> getComputers(String filtre, String companyFiltre);
}
