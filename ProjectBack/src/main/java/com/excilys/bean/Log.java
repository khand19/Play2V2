package com.excilys.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name = "LOG")
public class Log {
	@Id
	@GeneratedValue
	@Column(name = "idLog")
	private int idLog;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateLog")
	private Date dateLog;
	@Column(name = "optionLog")
	private String optionLog;
	@Column(name = "computerLog")

	private String computerLog;
	
	
	public int getIdLog() {
		return idLog;
	}
	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}
	public Date getDateLog() {
		return dateLog;
	}
	public void setDateLog(Date dateLog) {
		this.dateLog = dateLog;
	}
	public String getOptionLog() {
		return optionLog;
	}
	public void setOptionLog(String optionLog) {
		this.optionLog = optionLog;
	}
	public String getComputerLog() {
		return computerLog;
	}
	public void setComputerLog(String computerLog) {
		this.computerLog = computerLog;
	}

}
