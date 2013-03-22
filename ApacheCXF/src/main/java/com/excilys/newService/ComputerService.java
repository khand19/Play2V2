package com.excilys.newService;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.excilys.bean.Computer;

@WebService
public interface ComputerService {
	List<Computer> getComputers(@WebParam(name="filtre") String filtre, @WebParam(name="companyFiltre") String companyFiltre);

//	public List<Computer> getComputers(@WebParam(name="filtre") String filtre, @WebParam(name="companyFiltre")String companyFiltre);
	
}