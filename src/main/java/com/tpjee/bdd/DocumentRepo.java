package com.tpjee.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tpjee.models.Auteur;
import com.tpjee.models.Document;
import com.tpjee.models.DocumentResponse;
import com.tpjee.models.Domain;
import com.tpjee.models.MaisonEdition;
import com.tpjee.models.TypeDocument;

public class DocumentRepo {
	private Connection connexion;

	private String jdbcURL = "jdbc:mysql://localhost:3306/tpjee_db";
	private String jdbcDocumentnom = "root";
	private String jdbcPassword = "Gabydou@10";

	private static final String INSERT_DOCUMENT_SQL = "INSERT INTO documents" + "  (ISBN, libelle, description, auteur, domaine, maisonEdition,  type, cover) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_DOCUMENT_BY_ID = "SELECT documents.ISBN, documents.libelle, documents.description, documents.cover, auteurs.id as auteurId, auteurs.nationalite as nationaliteAuteur, auteurs.nom as nomAuteur, auteurs.prenom as prenomAuteur, maison_editions.id as maisonEditionId, maison_editions.nomMaison as nomMaison, type_documents.id as typeId, type_documents.designation as designationType, domaines.id as domaineId, domaines.designation as nomDomaine FROM documents, auteurs, domaines, type_documents, maison_editions where documents.auteur = auteurs.id AND documents.domaine=domaines.id AND documents.maisonEdition=maison_editions.id AND documents.type=type_documents.id AND documents.ISBN = ?;";
	
	private static final String SELECT_ALL_DOCUMENTS = "SELECT documents.ISBN, documents.libelle, documents.description, documents.cover, auteurs.id as auteurId, auteurs.nationalite as nationaliteAuteur, auteurs.nom as nomAuteur, auteurs.prenom as prenomAuteur, maison_editions.id as maisonEditionId, maison_editions.nomMaison as nomMaison, type_documents.id as typeId, type_documents.designation as designationType, domaines.id as domaineId, domaines.designation as nomDomaine FROM documents, auteurs, domaines, type_documents, maison_editions where documents.auteur = auteurs.id AND documents.domaine=domaines.id AND documents.maisonEdition=maison_editions.id AND documents.type=type_documents.id;";
	
	private static final String DELETE_DOCUMENT_SQL = "delete from documents where ISBN = ?;";
	private static final String UPDATE_DOCUMENT_SQL = "update documents set libelle = ?,description= ?, auteur =?, domaine = ?, maisonEdition= ?, type =?, cover= ?  where ISBN = ?;";
	
	private static final String DOCUMENTS_BY_DOMAINE = "SELECT documents.ISBN, documents.libelle, "
			+ "documents.description, documents.cover, "
			+ "auteurs.id as auteurId, auteurs.nationalite as nationaliteAuteur, "
			+ "auteurs.nom as nomAuteur, auteurs.prenom as prenomAuteur, "
			+ "maison_editions.id as maisonEditionId, maison_editions.nomMaison as nomMaison,"
			+ " type_documents.id as typeId, type_documents.designation as designationType,"
			+ " domaines.id as domaineId, domaines.designation as nomDomaine FROM documents,"
			+ " auteurs, domaines, type_documents, maison_editions"
			+ " where documents.auteur = auteurs.id "
			+ "AND documents.domaine=domaines.id"
			+ " AND documents.maisonEdition=maison_editions.id "
			+ "AND documents.type=type_documents.id "
			+ "AND documents.domaine = ? order by domaines.designation ASC;";
	
	private static final String DOCUMENTS_BY_AUTEUR = "SELECT documents.ISBN, documents.libelle, "
			+ "documents.description, documents.cover, "
			+ "auteurs.id as auteurId, auteurs.nationalite as nationaliteAuteur, "
			+ "auteurs.nom as nomAuteur, auteurs.prenom as prenomAuteur, "
			+ "maison_editions.id as maisonEditionId, maison_editions.nomMaison as nomMaison,"
			+ " type_documents.id as typeId, type_documents.designation as designationType,"
			+ " domaines.id as domaineId, domaines.designation as nomDomaine FROM documents,"
			+ " auteurs, domaines, type_documents, maison_editions"
			+ " where documents.auteur = auteurs.id "
			+ "AND documents.domaine=domaines.id"
			+ " AND documents.maisonEdition=maison_editions.id "
			+ "AND documents.type=type_documents.id "
			+ "AND documents.auteur = ? order by auteurs.id ASC;";
	
	private static final String DOCUMENTS_BY_MAISON = "SELECT documents.ISBN, documents.libelle, "
			+ "documents.description, documents.cover, "
			+ "auteurs.id as auteurId, auteurs.nationalite as nationaliteAuteur, "
			+ "auteurs.nom as nomAuteur, auteurs.prenom as prenomAuteur, "
			+ "maison_editions.id as maisonEditionId, maison_editions.nomMaison as nomMaison,"
			+ " type_documents.id as typeId, type_documents.designation as designationType,"
			+ " domaines.id as domaineId, domaines.designation as nomDomaine FROM documents,"
			+ " auteurs, domaines, type_documents, maison_editions"
			+ " where documents.auteur = auteurs.id "
			+ "AND documents.domaine=domaines.id"
			+ " AND documents.maisonEdition=maison_editions.id "
			+ "AND documents.type=type_documents.id "
			+ "AND documents.maisonEdition = ? order by maison_editions.nomMaison ASC;";
	
	private static final String DOCUMENTS_BY_TYPE = "SELECT documents.ISBN, documents.libelle, "
			+ "documents.description, documents.cover, "
			+ "auteurs.id as auteurId, auteurs.nationalite as nationaliteAuteur, "
			+ "auteurs.nom as nomAuteur, auteurs.prenom as prenomAuteur, "
			+ "maison_editions.id as maisonEditionId, maison_editions.nomMaison as nomMaison,"
			+ " type_documents.id as typeId, type_documents.designation as designationType,"
			+ " domaines.id as domaineId, domaines.designation as nomDomaine FROM documents,"
			+ " auteurs, domaines, type_documents, maison_editions"
			+ " where documents.auteur = auteurs.id "
			+ "AND documents.domaine=domaines.id"
			+ " AND documents.maisonEdition=maison_editions.id "
			+ "AND documents.type=type_documents.id "
			+ "AND documents.type = ? order by type_documents.designation ASC;";
	
	public List<DocumentResponse> listDocuments() {
		List<DocumentResponse> documents = new ArrayList<DocumentResponse>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();

			// Exécution de la requête
			resultat = statement.executeQuery(SELECT_ALL_DOCUMENTS);

			// Récupération des données
			while (resultat.next()) {
			   

				String ISBN = resultat.getString("ISBN");
				String libelle = resultat.getString("libelle");
				String description = resultat.getString("description");
				
				Auteur auteur = new Auteur(resultat.getInt("auteurId"), 
										resultat.getString("nomAuteur"), 
										resultat.getString("prenomAuteur"),
										resultat.getString("nationaliteAuteur")
										);
				
				MaisonEdition maisonEdition = new MaisonEdition(
										resultat.getInt("maisonEditionId"), 
										resultat.getString("nomMaison")
				);
				Domain domain = new Domain( 
										resultat.getInt("DomaineId"), 
										resultat.getString("nomDomaine")
				);
				TypeDocument typeDocument = new TypeDocument(
										resultat.getInt("typeId"), 
										resultat.getString("designationType")
				);				
				String cover = resultat.getString("cover");
				
				documents.add(new DocumentResponse(ISBN, libelle, description, domain, maisonEdition, auteur, typeDocument, cover));
			}
		} catch (SQLException e) {
			
		} finally {
			// Fermeture de la connexion
			try {
				if (resultat != null)
					resultat.close();
				if (statement != null)
					statement.close();
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}

		return documents;
	}
	
	
	public List<DocumentResponse> DocumentsByDomaine(int id) {
		List<DocumentResponse> document = new ArrayList<DocumentResponse>();
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(DOCUMENTS_BY_DOMAINE);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultat = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (resultat.next()) {
				String ISBN = resultat.getString("ISBN");
				String libelle = resultat.getString("libelle");
				String description = resultat.getString("description");
				
				Auteur auteur = new Auteur(resultat.getInt("auteurId"), 
										resultat.getString("nomAuteur"), 
										resultat.getString("prenomAuteur"),
										resultat.getString("nationaliteAuteur")
										);
				
				MaisonEdition maisonEdition = new MaisonEdition(
										resultat.getInt("maisonEditionId"), 
										resultat.getString("nomMaison")
				);
				Domain domain = new Domain( 
										resultat.getInt("DomaineId"), 
										resultat.getString("nomDomaine")
				);
				TypeDocument typeDocument = new TypeDocument(
										resultat.getInt("typeId"), 
										resultat.getString("designationType")
				);				
				
				String cover = resultat.getString("cover");
				
				document.add(new DocumentResponse(ISBN, libelle, description, domain, maisonEdition, auteur, typeDocument, cover));

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public List<DocumentResponse> DocumentsByAuteur(int id) {
		List<DocumentResponse> document = new ArrayList<DocumentResponse>();
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(DOCUMENTS_BY_AUTEUR);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultat = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (resultat.next()) {
				String ISBN = resultat.getString("ISBN");
				String libelle = resultat.getString("libelle");
				String description = resultat.getString("description");
				
				Auteur auteur = new Auteur(resultat.getInt("auteurId"), 
										resultat.getString("nomAuteur"), 
										resultat.getString("prenomAuteur"),
										resultat.getString("nationaliteAuteur")
										);
				
				MaisonEdition maisonEdition = new MaisonEdition(
										resultat.getInt("maisonEditionId"), 
										resultat.getString("nomMaison")
				);
				Domain domain = new Domain( 
										resultat.getInt("DomaineId"), 
										resultat.getString("nomDomaine")
				);
				TypeDocument typeDocument = new TypeDocument(
										resultat.getInt("typeId"), 
										resultat.getString("designationType")
				);				
				
				String cover = resultat.getString("cover");
				
				document.add(new DocumentResponse(ISBN, libelle, description, domain, maisonEdition, auteur, typeDocument, cover));

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return document;
	}

	public List<DocumentResponse> DocumentsByMaisonEdition(int id) {
		List<DocumentResponse> document = new ArrayList<DocumentResponse>();
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(DOCUMENTS_BY_MAISON);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultat = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (resultat.next()) {
				String ISBN = resultat.getString("ISBN");
				String libelle = resultat.getString("libelle");
				String description = resultat.getString("description");
				
				Auteur auteur = new Auteur(resultat.getInt("auteurId"), 
										resultat.getString("nomAuteur"), 
										resultat.getString("prenomAuteur"),
										resultat.getString("nationaliteAuteur")
										);
				
				MaisonEdition maisonEdition = new MaisonEdition(
										resultat.getInt("maisonEditionId"), 
										resultat.getString("nomMaison")
				);
				Domain domain = new Domain( 
										resultat.getInt("DomaineId"), 
										resultat.getString("nomDomaine")
				);
				TypeDocument typeDocument = new TypeDocument(
										resultat.getInt("typeId"), 
										resultat.getString("designationType")
				);				
				
				String cover = resultat.getString("cover");
				
				document.add(new DocumentResponse(ISBN, libelle, description, domain, maisonEdition, auteur, typeDocument, cover));

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public List<DocumentResponse> DocumentsByType(int id) {
		List<DocumentResponse> document = new ArrayList<DocumentResponse>();
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(DOCUMENTS_BY_TYPE);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultat = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (resultat.next()) {
				String ISBN = resultat.getString("ISBN");
				String libelle = resultat.getString("libelle");
				String description = resultat.getString("description");
				
				Auteur auteur = new Auteur(resultat.getInt("auteurId"), 
										resultat.getString("nomAuteur"), 
										resultat.getString("prenomAuteur"),
										resultat.getString("nationaliteAuteur")
										);
				
				MaisonEdition maisonEdition = new MaisonEdition(
										resultat.getInt("maisonEditionId"), 
										resultat.getString("nomMaison")
				);
				Domain domain = new Domain( 
										resultat.getInt("DomaineId"), 
										resultat.getString("nomDomaine")
				);
				TypeDocument typeDocument = new TypeDocument(
										resultat.getInt("typeId"), 
										resultat.getString("designationType")
				);				
				
				String cover = resultat.getString("cover");
				
				document.add(new DocumentResponse(ISBN, libelle, description, domain, maisonEdition, auteur, typeDocument, cover));

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return document;
	}


	

	public void insertDocument(Document document) {

		loadDatabase();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(INSERT_DOCUMENT_SQL);
			preparedStatement.setString(1, document.getISBN());
			preparedStatement.setString(2, document.getLibelle());
			preparedStatement.setString(3, document.getDescription());
			preparedStatement.setInt(4, document.getAuteur());
			preparedStatement.setInt(5, document.getDomaine());
			preparedStatement.setInt(6, document.getMaisonEdition());
			preparedStatement.setInt(7, document.getType());
			preparedStatement.setString(8, document.getCover());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public DocumentResponse selectDocument(String id) {
		DocumentResponse document = null;
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(SELECT_DOCUMENT_BY_ID);
			preparedStatement.setString(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultat = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (resultat.next()) {
				String ISBN = resultat.getString("ISBN");
				String libelle = resultat.getString("libelle");
				String description = resultat.getString("description");
				
				Auteur auteur = new Auteur(resultat.getInt("auteurId"), 
										resultat.getString("nomAuteur"), 
										resultat.getString("prenomAuteur"),
										resultat.getString("nationaliteAuteur")
										);
				
				MaisonEdition maisonEdition = new MaisonEdition(
										resultat.getInt("maisonEditionId"), 
										resultat.getString("nomMaison")
				);
				Domain domain = new Domain( 
										resultat.getInt("DomaineId"), 
										resultat.getString("nomDomaine")
				);
				TypeDocument typeDocument = new TypeDocument(
										resultat.getInt("typeId"), 
										resultat.getString("designationType")
				);				
				
				String cover = resultat.getString("cover");
				
				document = new DocumentResponse(ISBN, libelle, description, domain, maisonEdition, auteur, typeDocument, cover);

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return document;
	}

	public boolean updateDocument(Document document) throws SQLException {
		loadDatabase();
		boolean rowUpdated;
		try {
			PreparedStatement statement = connexion.prepareStatement(UPDATE_DOCUMENT_SQL);
			statement.setString(8, document.getISBN());
			statement.setString(1, document.getLibelle());
			statement.setString(2, document.getDescription());
			statement.setInt(3, document.getAuteur());
			statement.setInt(4, document.getDomaine());
			statement.setInt(5, document.getMaisonEdition());
			statement.setInt(6, document.getType());
			statement.setString(7, document.getCover());
			
			rowUpdated = statement.executeUpdate() > 0;
		} finally {

		}
		return rowUpdated;
	}

	public boolean deleteDocument(String id) throws SQLException {

		loadDatabase();
		boolean rowDeleted;
		try {
			PreparedStatement statement = connexion.prepareStatement(DELETE_DOCUMENT_SQL);
			statement.setString(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} finally {
		}
		return rowDeleted;
	}

	

	 
	private void loadDatabase() {
		// Chargement du driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			connexion = DriverManager.getConnection(jdbcURL, jdbcDocumentnom, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
