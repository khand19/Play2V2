package com.excilys.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.bean.Company;
import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@WebServlet("/SaveComputer")
public class SaveComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			c.setCompany(CompanyService.INSTANCE.getCompanyByID(Integer
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
			request.setAttribute("company", CompanyService.INSTANCE.getCompany());
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/jsp/InfoComputer.jsp")
					.forward(request, response);
		} else {
			if(request.getParameter("id") != null && request.getParameter("id")!=""){
				ComputerService.INSTANCE.updateComputer(c);
			}else{
				ComputerService.INSTANCE.addComputer(c);
			}
			
			request.setAttribute("message", 2);
			request.setAttribute("name", c.getNameComputer());			
			
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
				liste = ComputerService.INSTANCE.getComputers(f,numPage*10,s);			
			}else{
				liste = ComputerService.INSTANCE.getComputers(numPage*10,s);
			}
			request.setAttribute("computer", liste.getListeComputer());
			request.setAttribute("nbel",liste.getSize());
			request.setAttribute("numpage",numPage);

			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Computer.jsp").forward(request, response);
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
