package com.tpjee.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getServletPath();

		System.out.println(action);
		try {
			HttpSession session = request.getSession();

			if (session.getAttribute("currentSessionUser") != null) {

				switch (action) {

				case "/admin-index":
					adminHome(request, response);
					break;
				case "/deconnexion":
					logOut(request, response);
					break;
				
				default:
					adminHome(request, response);
					break;
				}
			} else {
				response.sendRedirect("login_form");

			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void adminHome(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		if(request.getServletPath() == "/admin_index") 
			request.setAttribute("activead", "active");
		else {
    		 request.setAttribute("activead", "");

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/adminIndex.jsp");
		dispatcher.forward(request, response);	
	}
	
	
	private void logOut(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		 HttpSession session = request.getSession();
	     session.invalidate();

	     /* Redirection vers le Site du Zéro ! */
	     response.sendRedirect( "login_form" );

	}
	

}
