package com.tpjee.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpjee.bdd.DocumentRepo;
import com.tpjee.bdd.DomainRepo;
import com.tpjee.models.DocumentResponse;
import com.tpjee.models.Domain;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	DocumentRepo documentRepo = new DocumentRepo();
	DomainRepo domainRepo = new DomainRepo();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		request.setAttribute("message", "");
		request.setAttribute("color", "");
		request.setAttribute("role", "");
		System.out.println(action);
		try {
			switch (action) {
			case "/documents_domain":
				listDocumentDomaines(request, response);
				break;
			case "/detail_document":
				detailDocument(request, response);

			case "/login_form":
				loginForm(request, response);
				break;
			case "/register_form":
				registerForm(request, response);
				break;

			default:
				listDomaines(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

	public void listDomaines(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Domain> domains = new ArrayList<Domain>();
		domains = domainRepo.listDomains();
		request.setAttribute("domains", domains);

		List<DocumentResponse> documents = new ArrayList<DocumentResponse>();

		HashMap<Domain, List<DocumentResponse>> documentByDomain = new HashMap<Domain, List<DocumentResponse>>();
		for (Domain domain : domains) {
			documents = documentRepo.DocumentsByDomaine(domain.getId());
			documentByDomain.put(domain, documents);
		}

		request.setAttribute("documentsByDomain", documentByDomain);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		dispatcher.forward(request, response);

	}

	public void detailDocument(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		DocumentResponse documents = new DocumentResponse();
		String id = (String) request.getParameter("id");
		documents = documentRepo.selectDocument(id);
		request.setAttribute("documents", documents);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/doc_detail.jsp");
		dispatcher.forward(request, response);
	}

	public void listDocumentDomaines(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));
		/*
		 * DocumentResponse existingDocument = documentRepo.DocumentsByDomaine(id);
		 */ List<DocumentResponse> documents = new ArrayList<DocumentResponse>();
		documents = documentRepo.DocumentsByDomaine(id);
		request.setAttribute("documents", documents);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/document.jsp");
		dispatcher.forward(request, response);

	}

	private void loginForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
		dispatcher.forward(request, response);
	}

	private void registerForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/register.jsp");
		dispatcher.forward(request, response);

	}

//	private HashMap<Integer, List<DocumentResponse>> document_by_domain(List<Domain> domains) {
//		List<DocumentResponse> documents = new ArrayList<DocumentResponse>();
//
//		HashMap<Integer, List<DocumentResponse>> documentByDomain = new HashMap<Integer, List<DocumentResponse>>();
//		for (Domain domain : domains) {
//			documents = documentRepo.DocumentsByDomaine(domain.getId());
//			documentByDomain.put(domain.getId(), documents);
//		}
//
//		return documentByDomain;
//	}

}
