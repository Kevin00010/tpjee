package com.tpjee.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpjee.bdd.Repository;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Repository tableNoms = new Repository();
       
        request.setAttribute("auteurs", tableNoms.recupererAuteurs());
        this.getServletContext().getRequestDispatcher("/WEB-INF/document.jsp").forward(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        Auteur auteur = new Auteur();
        auteur.setNom(request.getParameter("nom"));
        auteur.setPrenom(request.getParameter("prenom"));
        auteur.setNationalite(request.getParameter("nationalite"));
        Repository tableNoms = new Repository();
        tableNoms.ajouterAuteur(auteur);
        
        request.setAttribute("auteurs", tableNoms.recupererAuteurs());
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/document.jsp").forward(request, response);
    }

}
