package com.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;

@WebServlet("/ComputerId")
public class ComputerID extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public ComputerID() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null){
			request.setAttribute("company", new CompanyDAO().getCompany());
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/InfoComputer.jsp").forward( request, response );
		}else{		
			request.setAttribute("computer", new ComputerDAO().getComputerById(Integer.parseInt((String)request.getParameter("id"))));
			request.setAttribute("company", new CompanyDAO().getCompany());
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/InfoComputer.jsp").forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}


}
