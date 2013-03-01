package com.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.bean.ListComputer;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class Computers
 */
@WebServlet("/Computers")
public class Computers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerService computerService;
	
	
    public Computers() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numPage = 0;
		try {
			numPage = Integer.parseInt((String)request.getParameter("p"));
		} catch (Exception e) {
		}		
		
		double s = 0;
		if (request.getParameter("s") != null){
			s = Double.parseDouble((String) request.getParameter("s"));			
		}
		
		ListComputer liste = null;
		if (request.getParameter("f") != null){
			String f = request.getParameter("f");
			liste = computerService.getComputers(f,numPage*10,s);			
		}else{
			liste = computerService.getComputers(numPage*10,s);
		}
		request.setAttribute("computer", liste.getListeComputer());
		request.setAttribute("nbel",liste.getSize());
		request.setAttribute("numpage",numPage);
		
		if (request.getParameter("name") != null){
			request.setAttribute("message", 2);
			request.setAttribute("name", request.getParameter("name"));	
		}
		
		if (request.getParameter("delete") != null){
			request.setAttribute("message", 1);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Computer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
