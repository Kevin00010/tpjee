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

import com.tpjee.bdd.DomainRepo;
import com.tpjee.models.Domain;

/**
 * Servlet implementation class DomainController
 */
@WebServlet("/DomainController")
public class DomainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DomainController() {
        super();
        // TODO Auto-generated constructor stub
    }

    DomainRepo domainRepo = new DomainRepo();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	String action  = request.getServletPath();

   	System.out.println(action);
   		try {
   		 switch (action) {
         
         case "/insert_domain":
             insertDomain(request, response);
             break;
         case "/delete_domain":
             deleteDomain(request, response);
             break;
         case "/edit_domain":
             showEditForm(request, response);
             break;
         case "/update_domain":
             updateDomain(request, response);
             break;
         default:
             listDomains(request, response);
             break;
   		 }
   		} catch (SQLException ex) {
   			throw new ServletException(ex);
   		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		}

	private void listDomains(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Domain> domains = domainRepo.listDomains();
		request.setAttribute("domains", domains);
		if(request.getServletPath() == "/list_domain") 
			request.setAttribute("activeo", "active");
		else {
    		 request.setAttribute("activeo", "");

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/domain.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Domain existingDomain = domainRepo.selectDomain(id);
		List<Domain> domains = domainRepo.listDomains();
		request.setAttribute("domains", domains);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/editDomain.jsp");
		request.setAttribute("domain", existingDomain);
		dispatcher.forward(request, response);

	}

	private void insertDomain(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String designation = request.getParameter("designation");
		Domain newDomain = new Domain(designation);
		domainRepo.insertDomain(newDomain);
		response.sendRedirect("list_domain");
	}

	private void updateDomain(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String designation = request.getParameter("designation");
		Domain domain = new Domain(id, designation);
		domainRepo.updateDomain(domain);
		response.sendRedirect("list_domain");
	}

	private void deleteDomain(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		domainRepo.deleteDomain(id);
		List<Domain> domains = domainRepo.listDomains();
		request.setAttribute("domains", domains);
		response.sendRedirect("list_domain");
	}   
}
