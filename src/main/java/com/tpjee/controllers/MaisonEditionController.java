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

import com.tpjee.bdd.MaisonEditionRepo;
import com.tpjee.models.MaisonEdition;

/**
 * Servlet implementation class MaisonEditionController
 */
@WebServlet("/MaisonEditionController")
public class MaisonEditionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaisonEditionController() {
        super();
        // TODO Auto-generated constructor stub
    }

    MaisonEditionRepo maisonEditionRepo = new MaisonEditionRepo();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	String action  = request.getServletPath();

   	System.out.println(action);
   		try {
   			
HttpSession session = request.getSession();
   		    
   		    
   		    if(session.getAttribute("currentSessionUser") != null) {
   			
   		 switch (action) {
         
         case "/insert_maisonEdition":
             insertMaisonEdition(request, response);
             break;
         case "/delete_maisonEdition":
             deleteMaisonEdition(request, response);
             break;
         case "/edit_maisonEdition":
             showEditForm(request, response);
             break;
         case "/update_maisonEdition":
             updateMaisonEdition(request, response);
             break;
         default:
             listMaisonEditions(request, response);
             break;
   		 }
   		    }else {
   		    	response.sendRedirect("login_form");
   		    }
   		} catch (SQLException ex) {
   			throw new ServletException(ex);
   		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		}

	private void listMaisonEditions(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<MaisonEdition> maisonEditions = maisonEditionRepo.listMaisonEditions();
		request.setAttribute("maisonEditions", maisonEditions);
		if(request.getServletPath() == "/list_maisonEdition") 
			request.setAttribute("activem", "active");
		else {
    		 request.setAttribute("activem", "");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/maisonEdition.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		MaisonEdition existingMaisonEdition = maisonEditionRepo.selectMaisonEdition(id);
		List<MaisonEdition> maisonEditions = maisonEditionRepo.listMaisonEditions();
		request.setAttribute("maisonEditions", maisonEditions);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/editMaisonEdition.jsp");
		request.setAttribute("maisonEdition", existingMaisonEdition);
		dispatcher.forward(request, response);

	}

	private void insertMaisonEdition(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String nomMaison= request.getParameter("nomMaison");
		MaisonEdition newMaisonEdition = new MaisonEdition(nomMaison);
		maisonEditionRepo.insertMaisonEdition(newMaisonEdition);
		response.sendRedirect("list_maisonEdition");
	}

	private void updateMaisonEdition(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nomMaison= request.getParameter("nomMaison");
		MaisonEdition maisonEdition = new MaisonEdition(id, nomMaison);
		maisonEditionRepo.updateMaisonEdition(maisonEdition);
		response.sendRedirect("list_maisonEdition");
	}

	private void deleteMaisonEdition(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		maisonEditionRepo.deleteMaisonEdition(id);
		List<MaisonEdition> maisonEditions = maisonEditionRepo.listMaisonEditions();
		request.setAttribute("maisonEditions", maisonEditions);
		response.sendRedirect("list_maisonEdition");
	}   

}
