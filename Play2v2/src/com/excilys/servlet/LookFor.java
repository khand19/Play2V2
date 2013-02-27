package com.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.bean.Computer;
import com.excilys.dao.ComputerDAO;

@WebServlet("/LookFor")
public class LookFor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LookFor() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int numPage = 1;
		try {
			numPage = Integer.parseInt((String)request.getParameter("p"));
		} catch (Exception e) {
		}		
		numPage--;
		
		String f = request.getParameter("f");
//		List<Computer> l = new ComputerDAO().getComputers(f,numPage*10);
//		request.setAttribute("computer", l);
		
		int nbEl = new ComputerDAO().getNbPages(f);
		request.setAttribute("nbel",nbEl);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Computer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}