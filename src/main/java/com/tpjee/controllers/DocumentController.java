package com.tpjee.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.tpjee.bdd.AuteurRepo;
import com.tpjee.bdd.DocumentRepo;
import com.tpjee.bdd.DomainRepo;
import com.tpjee.bdd.MaisonEditionRepo;
import com.tpjee.bdd.TypeDocumentRepo;
import com.tpjee.models.Auteur;
import com.tpjee.models.Document;
import com.tpjee.models.DocumentResponse;
import com.tpjee.models.Domain;
import com.tpjee.models.MaisonEdition;
import com.tpjee.models.TypeDocument;

/**
 * Servlet implementation class DocumentController
 */
@WebServlet("/DocumentController")
public class DocumentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "/Users/armandpountie/eclipse-workspace/tpjee/src/main/webapp/resources/assets/images/covers/";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentController() {
        super();
        // TODO Auto-generated constructor stub
    }

    DocumentRepo documentRepo = new DocumentRepo();
    DomainRepo domainRepo = new DomainRepo();
    AuteurRepo auteurRepo = new AuteurRepo();
    MaisonEditionRepo maisonEditionRepo = new MaisonEditionRepo();
    TypeDocumentRepo typeDocumentRepo = new TypeDocumentRepo();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	String action  = request.getServletPath();
   	System.out.println(action);
	System.out.println(getServletContext().getContextPath());

   		try {
   		 switch (action) {
         
         case "/insert_document":
             insertDocument(request, response);
             break;
         case "/delete_document":
             deleteDocument(request, response);
             break;
         case "/edit_document":
             showEditForm(request, response);
             break;
         case "/update_document":
             updateDocument(request, response);
             break;
         default:
             listDocuments(request, response);
             break;
   		 }
   		} catch (SQLException ex) {
   			throw new ServletException(ex);
   		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		}
	
	private void listDocuments(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		

		List<DocumentResponse> documents = documentRepo.listDocuments();
		request.setAttribute("documents", documents);
		List<Auteur> auteurs = auteurRepo.listAuteurs();
		request.setAttribute("auteurs", auteurs);
		List<Domain> domains = domainRepo.listDomains();
		request.setAttribute("domains", domains);
		List<MaisonEdition> maisonEditions = maisonEditionRepo.listMaisonEditions();
		request.setAttribute("maisonEditions", maisonEditions);
		List<TypeDocument> typeDocuments = typeDocumentRepo.listTypeDocuments();
		request.setAttribute("typeDocuments", typeDocuments);
		if(request.getServletPath() == "/list_document") 
			request.setAttribute("actived", "active");
		else {
    		 request.setAttribute("actived", "");

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/document.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id = (String) request.getParameter("id");
		DocumentResponse existingDocument = documentRepo.selectDocument(id);
		
		List<DocumentResponse> documents = documentRepo.listDocuments();
		request.setAttribute("documents", documents);

		List<Auteur> auteurs = auteurRepo.listAuteurs();
		request.setAttribute("auteurs", auteurs);
		List<Domain> domains = domainRepo.listDomains();
		request.setAttribute("domains", domains);
		List<MaisonEdition> maisonEditions = maisonEditionRepo.listMaisonEditions();
		request.setAttribute("maisonEditions", maisonEditions);
		List<TypeDocument> typeDocuments = typeDocumentRepo.listTypeDocuments();
		request.setAttribute("typeDocuments", typeDocuments);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/editDocument.jsp");
		request.setAttribute("document", existingDocument);
		dispatcher.forward(request, response);

	}

	private void insertDocument(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		try {
	
       
		Part part = request.getPart("cover");
        // On vérifie qu'on a bien reçu un fichier
        String cover = getNomFichier(part);
        System.out.println(cover);
        // Si on a bien un fichier
        if (cover != null && !cover.isEmpty()) {
            String nomChamp = part.getName();
            System.out.println(nomChamp);
            // Corrige un bug du fonctionnement d'Internet Explorer
            cover = cover.substring(cover.lastIndexOf('/') + 1)
                    .substring(cover.lastIndexOf('\\') + 1);
             System.out.println(cover);

            // On écrit définitivement le fichier sur le disque
            ecrireFichier(part, cover, CHEMIN_FICHIERS);
        }else {
        	cover = "no-image.png";
        }
		
		String ISBN = request.getParameter("ISBN");
		String libelle = request.getParameter("libelle");
		String description = request.getParameter("description");
		int auteur = Integer.parseInt(request.getParameter("auteur"));
		int domaine = Integer.parseInt(request.getParameter("domaine"));
		int maisonEdition = Integer.parseInt(request.getParameter("maisonEdition"));
		int type = Integer.parseInt(request.getParameter("typeDocument"));
	   
		
		Document newDocument = new Document(ISBN, libelle, description, auteur, domaine, maisonEdition, type, cover);
		request.setAttribute("message", "Document successfully added!");
		request.setAttribute("color", "green");
		
		documentRepo.insertDocument(newDocument);
		}catch(IOException e) {
			throw new IOException (e);
		}catch(ServletException e) {
			throw new ServletException (e);
		}catch(Exception e) {
			request.setAttribute("message", "Internal Server Error");
			request.setAttribute("color", "red");
		}
		response.sendRedirect("list_document");
	}

	private void updateDocument(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		Part part = request.getPart("cover");
        // On vérifie qu'on a bien reçu un fichier
        String cover = getNomFichier(part);
        System.out.println(cover);
        // Si on a bien un fichier
        if (cover != null && !cover.isEmpty()) {
            String nomChamp = part.getName();
            System.out.println(nomChamp);
            // Corrige un bug du fonctionnement d'Internet Explorer
            cover = cover.substring(cover.lastIndexOf('/') + 1)
                    .substring(cover.lastIndexOf('\\') + 1);
             System.out.println(cover);

            // On écrit définitivement le fichier sur le disque
            ecrireFichier(part, cover, CHEMIN_FICHIERS);
        }else {
        	cover = "no-image.png";
        }
		
		
		String ISBN = request.getParameter("ISBN");
		String libelle = request.getParameter("libelle");
		String description = request.getParameter("description");
		int domaine = Integer.parseInt(request.getParameter("domaine"));
		int auteur = Integer.parseInt(request.getParameter("auteur"));
		int maisonEdition = Integer.parseInt(request.getParameter("maisonEdition"));
		int type = Integer.parseInt(request.getParameter("typeDocument"));

		Document updatedDocument = new Document(ISBN, libelle, description, auteur, domaine, maisonEdition, type, cover);

		documentRepo.updateDocument(updatedDocument);
		response.sendRedirect("list_document");
	}

	private void deleteDocument(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		String id = (String) request.getParameter("id");
		documentRepo.deleteDocument(id);
		response.sendRedirect("list_document");
	}   
	
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
    
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }  
    
   


}
