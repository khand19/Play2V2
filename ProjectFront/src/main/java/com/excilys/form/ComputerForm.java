package com.excilys.form;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.excilys.bean.Company;
import com.excilys.bean.Computer;

public class ComputerForm {
	@Id
	@GeneratedValue
	private int idComputer;

	@NotEmpty
	private String nameComputer;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date introducedDate;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dscountedDate;
	
	private Integer company;

	public ComputerForm(){
		super();
	}
	
	public ComputerForm(int idComputer, String nameComputer,
			Date introducedDate, Date dscountedDate, Integer company) {
		super();
		this.idComputer = idComputer;
		this.nameComputer = nameComputer;
		this.introducedDate = introducedDate;
		this.dscountedDate = dscountedDate;
		this.company = company;
	}

	public ComputerForm(Computer computer) {
		this.idComputer = computer.getIdComputer();
		this.nameComputer = computer.getNameComputer();
		this.introducedDate = computer.getIntroducedDate();
		this.dscountedDate = computer.getDscountedDate();
		if(computer.getCompany()!=null)
			this.company = computer.getCompany().getIdCompany();
	}

	public int getIdComputer() {
		return idComputer;
	}

	public void setIdComputer(int idComputer) {
		this.idComputer = idComputer;
	}

	public String getNameComputer() {
		return nameComputer;
	}

	public void setNameComputer(String nameComputer) {
		this.nameComputer = nameComputer;
	}

	public Date getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(Date introducedDate) {
		this.introducedDate = introducedDate;
	}

	public Date getDscountedDate() {
		return dscountedDate;
	}

	public void setDscountedDate(Date dscountedDate) {
		this.dscountedDate = dscountedDate;
	}

	public Integer getCompany() {
		return company;
	}

	public void setCompany(Integer company) {
		this.company = company;
	}
	

	@Override
	public String toString() {
		return "ComputerForm [idComputer=" + idComputer + ", nameComputer="
				+ nameComputer + ", introducedDate=" + introducedDate
				+ ", dscountedDate=" + dscountedDate + ", company=" + company
				+ "]";
	}

	public Computer convertToComputer(Company company) {
		return new Computer(idComputer,nameComputer,introducedDate,dscountedDate,company);
	}

	public Computer convertToComputerToAdd(Company company) {
		return new Computer(nameComputer,introducedDate,dscountedDate,company);
	}
}
