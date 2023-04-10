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

import com.tpjee.bdd.TypeDocumentRepo;
import com.tpjee.models.TypeDocument;

/**
 * Servlet implementation class TypeDocumentController
 */
@WebServlet("/TypeDocumentController")
public class TypeDocumentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeDocumentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    TypeDocumentRepo typeDocumentRepo = new TypeDocumentRepo();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	String action  = request.getServletPath();

   	System.out.println(action);
   		try {
   		 switch (action) {
         
         case "/insert_type_document":
             insertTypeDocument(request, response);
             break;
         case "/delete_type_document":
             deleteTypeDocument(request, response);
             break;
         case "/edit_type_document":
             showEditForm(request, response);
             break;
         case "/update_type_document":
             updateTypeDocument(request, response);
             break;
         default:
             listTypeDocuments(request, response);
             break;
   		 }
   		} catch (SQLException ex) {
   			throw new ServletException(ex);
   		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		}

	private void listTypeDocuments(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<TypeDocument> typeDocuments = typeDocumentRepo.listTypeDocuments();
		request.setAttribute("typeDocuments", typeDocuments);
		if(request.getServletPath() == "/list_type_document") 
			request.setAttribute("activet", "active");
		else {
    		 request.setAttribute("activet", "");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/type_document.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		TypeDocument existingTypeDocument = typeDocumentRepo.selectTypeDocument(id);
		List<TypeDocument> typeDocuments = typeDocumentRepo.listTypeDocuments();
		request.setAttribute("typeDocuments", typeDocuments);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/edit_type_document.jsp");
		request.setAttribute("typeDocument", existingTypeDocument);
		dispatcher.forward(request, response);

	}

	private void insertTypeDocument(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String designation = request.getParameter("designation");
		TypeDocument newTypeDocument = new TypeDocument(designation);
		typeDocumentRepo.insertTypeDocument(newTypeDocument);
		response.sendRedirect("list_type_document");
	}

	private void updateTypeDocument(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String designation = request.getParameter("designation");
		TypeDocument typeDocument = new TypeDocument(id, designation);
		typeDocumentRepo.updateTypeDocument(typeDocument);
		response.sendRedirect("list_type_document");
	}

	private void deleteTypeDocument(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		typeDocumentRepo.deleteTypeDocument(id);
		List<TypeDocument> typeDocuments = typeDocumentRepo.listTypeDocuments();
		request.setAttribute("typeDocuments", typeDocuments);
		response.sendRedirect("list_type_document");
	}   
	
	
}
