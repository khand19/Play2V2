package com.excilys.exception;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErreurPerso extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ErreurPerso(Exception s, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
		String st ="";
		for (int i = 0; i < s.getStackTrace().length; i++) {
			st += s.getStackTrace()[i] + "<br>";
		}
		request.setAttribute("nbel",s.getMessage());
		request.setAttribute("message",s.getLocalizedMessage());
		request.setAttribute("error",st);
		servletContext.getRequestDispatcher("/WEB-INF/jsp/Erreur.jsp").forward(request, response);
	}
}