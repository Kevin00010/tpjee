package com.tpjee.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tpjee.bdd.AuteurRepo;
import com.tpjee.models.Auteur;

/**
 * Servlet implementation class AuteurController
 */
@WebServlet("/AuteurController")
public class AuteurController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuteurController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	AuteurRepo auteurRepo = new AuteurRepo();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println(action);
		try {
			HttpSession session = request.getSession();

			if (session.getAttribute("currentSessionUser") != null) {

				switch (action) {

				case "/insert_auteur":
					insertAuteur(request, response);
					break;
				case "/delete_auteur":
					deleteAuteur(request, response);
					break;
				case "/edit_auteur":
					showEditForm(request, response);
					break;
				case "/update_auteur":
					updateAuteur(request, response);
					break;
				default:
					listAuteurs(request, response);
					break;
				}
			} else {
				response.sendRedirect("login_form");

			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void listAuteurs(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Auteur> auteurs = auteurRepo.listAuteurs();
		List<String> nationalites = auteurRepo.listNationalites();
		request.setAttribute("auteurs", auteurs);
		request.setAttribute("nationalites", nationalites);
		
		if (request.getServletPath() == "/list_auteur")
			request.setAttribute("activea", "active");
		else {
			request.setAttribute("activea", "");

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/auteur.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Auteur existingAuteur = auteurRepo.selectAuteur(id);
		List<Auteur> auteurs = auteurRepo.listAuteurs();
		List<String> nationalites = auteurRepo.listNationalites();
		request.setAttribute("nationalites", nationalites);
		request.setAttribute("auteurs", auteurs);
		if (request.getServletPath() == "/edit_auteur")
			request.setAttribute("activea", "active");
		else {
			request.setAttribute("activea", "");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/editAuteur.jsp");
		request.setAttribute("auteur", existingAuteur);
		dispatcher.forward(request, response);

	}

	private void insertAuteur(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		if (request.getServletPath() == "/insert_auteur")
			request.setAttribute("activea", "active");
		else {
			request.setAttribute("activea", "");
		}

		try {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String nationalite = request.getParameter("nationalite");
			Auteur newAuteur = new Auteur(nom, prenom, nationalite);
			auteurRepo.insertAuteur(newAuteur);

			request.setAttribute("message", "Successfully added!");
			request.setAttribute("color", "alert alert-success");
			request.setAttribute("role", "alert");
		} catch (Exception e) {
			request.setAttribute("message", "Internal server error!");
			request.setAttribute("color", "alert alert-danger");
			request.setAttribute("role", "alert");
		}
		
		List<Auteur> auteurs = auteurRepo.listAuteurs();
		request.setAttribute("auteurs", auteurs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/auteur.jsp");
		dispatcher.forward(request, response);

	}

	private void updateAuteur(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String nationalite = request.getParameter("nationalite");

		Auteur auteur = new Auteur(id, nom, prenom, nationalite);
		auteurRepo.updateAuteur(auteur);
		response.sendRedirect("list_auteur");
	}

	private void deleteAuteur(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		auteurRepo.deleteAuteur(id);
		List<Auteur> auteurs = auteurRepo.listAuteurs();
		request.setAttribute("auteurs", auteurs);
		response.sendRedirect("list_auteur");
	}

}
