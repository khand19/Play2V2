package com.excilys.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Computer {
	private int idComputer;
	private String nameComputer;
	private Date introducedDate;
	private Date dscountedDate;
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
}
