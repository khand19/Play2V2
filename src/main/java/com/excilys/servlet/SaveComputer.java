package com.excilys.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.bean.Company;
import com.excilys.bean.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@WebServlet("/SaveComputer")
public class SaveComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;

	public SaveComputer() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Computer c = new Computer();

		if(request.getParameter("id") != null && request.getParameter("id")!=""){
			c.setIdComputer(Integer.parseInt((String) request.getParameter("id")));
		}
		if(!request.getParameter("company").equals("")){
			c.setCompany(companyService.getCompanyByID(Integer
				.parseInt((String) request.getParameter("company"))));
		}else{
			c.setCompany(new Company(0,""));
		}
		
		boolean erreur = false;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		if(request.getParameter("introduced").equals(null) || request.getParameter("introduced").equals(""))
			c.setIntroducedDate(null);
		else{
			try {
				Date d = simpleDateFormat.parse(request.getParameter("introduced"));
				c.setIntroducedDate(d);
			} catch (Exception e) {
				erreur = true;
				request.setAttribute("introducedError", 1);
				request.setAttribute("introducedValue",
						request.getParameter("introduced"));
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("discontinued").equals(null) || request.getParameter("discontinued").equals(""))
			c.setDscountedDate(null);
		else{
			try {
				Date d = simpleDateFormat.parse(request.getParameter("discontinued"));
				c.setDscountedDate(d);
			} catch (Exception e) {
				erreur = true;
				request.setAttribute("discontinuedError", 1);
				request.setAttribute("discontinuedValue",
						request.getParameter("discontinued"));
				e.printStackTrace();
			}
		}

		c.setNameComputer(request.getParameter("name"));
		if (erreur) {
			request.setAttribute("computer", c);
			request.setAttribute("company", companyService.getCompany());
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/jsp/InfoComputer.jsp")
					.forward(request, response);
		} else {
			if(request.getParameter("id") != null && request.getParameter("id")!=""){
				computerService.updateComputer(c);
			}else{
				computerService.addComputer(c);
			}
			response.sendRedirect("Computers?name="+c.getNameComputer());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
