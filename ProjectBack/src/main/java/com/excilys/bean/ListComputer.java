package com.excilys.bean;

import java.util.List;

public class ListComputer {
	private List<Computer> listeComputer;
	private int size;
	
	public ListComputer(List<Computer> list, int siz){
		listeComputer = list;
		size = siz;
	}
	
	public List<Computer> getListeComputer() {
		return listeComputer;
	}
	public void setListeComputer(List<Computer> listeComputer) {
		this.listeComputer = listeComputer;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
