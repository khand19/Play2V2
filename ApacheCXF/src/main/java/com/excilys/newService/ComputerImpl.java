package com.excilys.newService;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.bean.Computer;
import com.excilys.service.IComputerService;

@WebService(endpointInterface = "com.excilys.newService.ComputerService", serviceName = "computerService")
//@WebService(endpointInterface = "com.excilys.webservice.ComputerWebService", serviceName = "computerWebService")

public class ComputerImpl implements ComputerService{
	
	@Autowired
	private IComputerService iComputerService;
	
	public List<Computer> getComputers(String filtre, String companyFiltre) {
		return iComputerService.getComputers(filtre, companyFiltre);
//		return new ComputerDAO().getComputers(paramComputer, paramCompany, null).getListeComputer();
	}
}
