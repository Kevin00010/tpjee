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

import com.tpjee.bdd.UtilisateurRepo;
import com.tpjee.models.Utilisateur;

/**
 * Servlet implementation class AuthController
 */
@WebServlet("/AuthController")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthController() {
		super();
		// TODO Auto-generated constructor stub
	}

	UtilisateurRepo utilisateurRepo = new UtilisateurRepo();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getServletPath();

		
		System.out.println(action);
		try {
			switch (action) {

			case "/login":
				login(request, response);
				break;
			case "/register":
				register(request, response);
				break;
			case "/admin_index":
				adminHome(request, response);
				break;
			case "/deconnexion":
				logOut(request, response);
				break;
			default:
				admin(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		Utilisateur utilisateur = new Utilisateur();

		utilisateur = utilisateurRepo.loginCheck(email, password);

		if (utilisateur != null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("currentSessionUser", utilisateur);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/adminIndex.jsp");
			dispatcher.forward(request, response);
		}
		
		request.setAttribute("message", "Username or password incorrect!");
		request.setAttribute("color", "alert alert-danger");
		request.setAttribute("role", "alert");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
		dispatcher.forward(request, response);
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		if (request.getServletPath() == "/insert_utilisateur")
			request.setAttribute("activea", "active");
		else {
			request.setAttribute("activea", "");
		}

		try {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, password);
			utilisateurRepo.insertUtilisateur(newUtilisateur);

			request.setAttribute("message", "Successfully added!");
			request.setAttribute("color", "alert alert-success");
			request.setAttribute("role", "alert");
		} catch (Exception e) {
			request.setAttribute("message", "Internal server error!");
			request.setAttribute("color", "alert alert-danger");
			request.setAttribute("role", "alert");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/register.jsp");
		dispatcher.forward(request, response);

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
	
	private void admin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
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
