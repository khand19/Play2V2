package com.excilys.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "COMPUTER")
public class Computer {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int idComputer;
	
    @NotEmpty
	@Column(name = "NAME")
	private String nameComputer;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "INTRODUCED")
	private Date introducedDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DISCONTINUED")
	private Date dscountedDate;
	
	@ManyToOne
    @JoinColumn(name="IDCOMPANY")
	private Company company;
	
	public Computer(){
		introducedDate = null;
		dscountedDate = null;
	}
	
	public Computer(int idComputer2, String nameComputer2,
			Date introducedDate2, Date dscountedDate2,Company company2) {
		idComputer = idComputer2;
		nameComputer = nameComputer2;
		introducedDate = introducedDate2;
		dscountedDate = dscountedDate2;
		company = company2;
	}
	
	public Computer(String nameComputer2,
			Date introducedDate2, Date dscountedDate2,Company company2) {
		nameComputer = nameComputer2;
		introducedDate = introducedDate2;
		dscountedDate = dscountedDate2;
		company = company2;
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
		try {
			if(company == null && company.getIdCompany()==0)
				return null;
		} catch (Exception e) {
				return null;
		}
		return company;
	}
	public void setCompany(Company company) {
		try {
			if(company.getIdCompany()==0)
				this.company = null;
		} catch (Exception e) {
			this.company =  null;
		}
		this.company = company;
		System.out.println(this.toString());
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((dscountedDate == null) ? 0 : dscountedDate.hashCode());
		result = prime * result + idComputer;
		result = prime * result
				+ ((introducedDate == null) ? 0 : introducedDate.hashCode());
		result = prime * result
				+ ((nameComputer == null) ? 0 : nameComputer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (dscountedDate == null) {
			if (other.dscountedDate != null)
				return false;
		} else if (!dscountedDate.equals(other.dscountedDate))
			return false;
		if (idComputer != other.idComputer)
			return false;
		if (introducedDate == null) {
			if (other.introducedDate != null)
				return false;
		} else if (!introducedDate.equals(other.introducedDate))
			return false;
		if (nameComputer == null) {
			if (other.nameComputer != null)
				return false;
		} else if (!nameComputer.equals(other.nameComputer))
			return false;
		return true;
	}
}
