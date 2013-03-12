package com.excilys.form;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import com.excilys.bean.Company;
import com.excilys.bean.Computer;

public class ComputerForm {
	@Id
	@GeneratedValue
	@NotNull
	private int idComputer;

	@NotEmpty
	private String nameComputer;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate introducedDate;

	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dscountedDate;
	
	private Integer company;

	public ComputerForm(){
		super();
	}
	
	public ComputerForm(int idComputer, String nameComputer,
			LocalDate introducedDate, LocalDate dscountedDate, Integer company) {
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

	public LocalDate getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(LocalDate introducedDate) {
		this.introducedDate = introducedDate;
	}

	public LocalDate getDscountedDate() {
		return dscountedDate;
	}

	public void setDscountedDate(LocalDate dscountedDate) {
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
