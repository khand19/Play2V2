package com.excilys.bean;

public class Company {
	private int idCompany;
	private String nameCompany;
	
	public Company(int int1, String string) {
		idCompany = int1;
		nameCompany = string;
	}
	
	public Company() {
		super();
		idCompany = 0;
	}
	
	
	public int getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}
	public String getNameCompany() {
		return nameCompany;
	}
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	
	
	@Override
	public String toString() {
		return "Company [idCompany=" + idCompany + ", nameCompany="
				+ nameCompany + "]";
	}
}