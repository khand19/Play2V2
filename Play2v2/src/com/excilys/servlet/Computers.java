package com.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.bean.Computer;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class Computers
 */
@WebServlet("/Computers")
public class Computers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Computers() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numPage = 0;
		try {
			numPage = Integer.parseInt((String)request.getParameter("p"));
		} catch (Exception e) {
		}		
		
		List<Computer> l;
		int nbEl;
		
		double s = 0;
		if (request.getParameter("s") != null){
			s = Double.parseDouble((String) request.getParameter("s"));			
		}
		
		if (request.getParameter("f") != null){
			String f = request.getParameter("f");
			l = new ComputerService().getComputers(f,numPage*10,s);
			nbEl = new ComputerService().getNbPages(f);
		}else{
			l = new ComputerService().getComputers(numPage*10,s);
			nbEl = new ComputerService().getNbPages("");
		}
		request.setAttribute("computer", l);
		request.setAttribute("nbel",nbEl);
		request.setAttribute("numpage",numPage);


		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Computer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
