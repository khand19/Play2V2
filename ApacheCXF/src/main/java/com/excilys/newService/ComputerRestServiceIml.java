package com.excilys.newService;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.model.ComputerList;
import com.excilys.service.IComputerService;

public class ComputerRestServiceIml implements ComputerRestService{

	@Autowired
	private IComputerService iComputerService;
	

	public ComputerList getComputers(
			 String filtre,
			 String companyFiltre) {
		return new ComputerList(iComputerService.getComputers(filtre, companyFiltre));
	}


	public ComputerList getComputersByComputer( String filtre) {
		return new ComputerList(iComputerService.getComputers(filtre, null));
	}


	public ComputerList getComputersByCompany(
			String companyFiltre) {
		return new ComputerList(iComputerService.getComputers(null, companyFiltre));

	}

}
