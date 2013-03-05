package com.excilys.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "COMPUTER")
public class Computer {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int idComputer;
	@Column(name = "NAME")
	private String nameComputer;
	@Temporal(TemporalType.DATE)
	@Column(name = "INTRODUCED")
	private Date introducedDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "DISCONTINUED")
	private Date dscountedDate;
	@Column(name = "IDCOMPANY")
	private Company company;
	
	public Computer(){
		introducedDate = null;
		dscountedDate = null;
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
	public Company getCompany() {
		if(company.getIdCompany()==0)
			return new Company();
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getIntroduceDateWithFormat(){
		DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		if(introducedDate!=null)
			return (String)formatter.format(introducedDate);
		else
			return "-";
	}
	public String getDiscountedsDateWithFormat(){
		DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		if(dscountedDate!=null)
			return (String)formatter.format(dscountedDate);
		else
			return "-";
	}

	@Override
	public String toString() {
		return "Computer [idComputer=" + idComputer + ", nameComputer="
				+ nameComputer + ", introducedDate=" + introducedDate
				+ ", dscountedDate=" + dscountedDate + ", company=" + company
				+ "]";
	}
	
	public boolean equals(Object o){
		return false;
	}
}
