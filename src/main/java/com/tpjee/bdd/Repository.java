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
import com.tpjee.models.MaisonEdition;
import com.tpjee.models.TypeDocument;
import com.tpjee.models.Utilisateur;

public class Repository {
	private Connection connexion;

	
	//Utilisateurs
	public List<Utilisateur> recupererUtilisateurs() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();

			// Exécution de la requête
			resultat = statement.executeQuery("SELECT id, nom, prenom FROM noms;");

			// Récupération des données
			while (resultat.next()) {
				Integer id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setId(id);
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);

				utilisateurs.add(utilisateur);
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

		return utilisateurs;
	}

	

	public void ajouterUtilisateur(Utilisateur utilisateur) {
		loadDatabase();

		try {
			PreparedStatement preparedStatement = connexion
					.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
			preparedStatement.setString(1, utilisateur.getNom());
			preparedStatement.setString(2, utilisateur.getPrenom());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Auteurs
	
	public List<Auteur> recupererAuteurs() {
		List<Auteur> auteurs = new ArrayList<Auteur>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();

			// Exécution de la requête
			resultat = statement.executeQuery("SELECT id, nom, prenom, nationalite FROM auteurs;");

			// Récupération des données
			while (resultat.next()) {
				Integer id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String nationalite = resultat.getString("nationalite");

				Auteur auteur = new Auteur();
				auteur.setId(id);
				auteur.setNom(nom);
				auteur.setPrenom(prenom);
				auteur.setNationalite(nationalite);

				auteurs.add(auteur);
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

		return auteurs;
	}

	
	public void ajouterAuteur(Auteur auteur) {
		loadDatabase();

		try {
			PreparedStatement preparedStatement = connexion
					.prepareStatement("INSERT INTO auteurs(nom, prenom, nationalite) VALUES(?, ?, ?);");
			preparedStatement.setString(1, auteur.getNom());
			preparedStatement.setString(2, auteur.getPrenom());
			preparedStatement.setString(3, auteur.getNationalite());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Maison Edition
	
		public List<MaisonEdition> recupererMaisonEdition() {
			List<MaisonEdition> maisonEditions = new ArrayList<MaisonEdition>();
			Statement statement = null;
			ResultSet resultat = null;

			loadDatabase();

			try {
				statement = connexion.createStatement();

				// Exécution de la requête
				resultat = statement.executeQuery("SELECT id, nomMaison, FROM MaisonEdition;");

				// Récupération des données
				while (resultat.next()) {
					Integer id = resultat.getInt("id");
					String nomMaison = resultat.getString("nomMaison");
					
					MaisonEdition maisonEdition = new MaisonEdition();
					maisonEdition.setId(id);
					maisonEdition.setNomMaison(nomMaison);
					
					maisonEditions.add(maisonEdition);
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

			return maisonEditions;
		}

		
		public void ajouterMaisonEdition(MaisonEdition maisonEdition) {
			loadDatabase();

			try {
				PreparedStatement preparedStatement = connexion
						.prepareStatement("INSERT INTO MaisonEdition( nomMaison) VALUES(?);");
				preparedStatement.setString(1, maisonEdition.getNomMaison());

				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		//TypeDocument
		
		public List<TypeDocument> recupererTypeDocument() {
			List<TypeDocument> typeDocuments = new ArrayList<TypeDocument>();
			Statement statement = null;
			ResultSet resultat = null;

			loadDatabase();

			try {
				statement = connexion.createStatement();

				// Exécution de la requête
				resultat = statement.executeQuery("SELECT id, designation FROM type_documents;");

				// Récupération des données
				while (resultat.next()) {
					Integer id = resultat.getInt("id");
					String designation = resultat.getString("designation");
					
					TypeDocument typeDocument = new TypeDocument();
					typeDocument.setId(id);
					typeDocument.setDesignation(designation);
					

					typeDocuments.add(typeDocument);
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

			return typeDocuments;
		}

		
		public void ajouterTypeDocument(TypeDocument typeDocument) {
			loadDatabase();

			try {
				PreparedStatement preparedStatement = connexion
						.prepareStatement("INSERT INTO (designation) VALUES(?);");
				preparedStatement.setString(1, typeDocument.getDesignation());
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public List<Document> recupererDocument() {
			List<Document> documents = new ArrayList<Document>();
			Statement statement = null;
			ResultSet resultat = null;

			loadDatabase();

			try {
				statement = connexion.createStatement();

				// Exécution de la requête
				resultat = statement.executeQuery("SELECT ISBN, libelle, description, domaine, maisonEdition, auteur, type FROM document;");

				// Récupération des données
				while (resultat.next()) {
					String id = resultat.getString("ISBN");
					String libelle = resultat.getString("libelle");
					String description = resultat.getString("description");
					String domaine = resultat.getString("domaine");
					Integer maisonEdition = resultat.getInt("maisonEdition");
					Integer auteur = resultat.getInt("auteur");
					Integer type = resultat.getInt("type");
					
					Document document = new Document();
					
					document.setISBN(id);
					document.setLibelle(libelle);
					document.setDescription(description);
					document.setDomaine(domaine);
					document.setMaisonEdition(maisonEdition);
					document.setAuteur(auteur);
					document.setType(type);
					
					documents.add(document);
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

		
		public void ajouterDocument(Document document) {
			loadDatabase();

			try {
				PreparedStatement preparedStatement = connexion
						.prepareStatement("INSERT INTO (ISBN, libelle, description, domaine, maisonEdition, auteur, type) VALUES(?, ?, ?, ?, ?, ?, ?);");
				preparedStatement.setString(1, document.getISBN());
				preparedStatement.setString(2, document.getLibelle());
				preparedStatement.setString(3, document.getDescription());
				preparedStatement.setString(4, document.getDomaine());
				preparedStatement.setInt(5, document.getMaisonEdition());
				preparedStatement.setInt(6, document.getAuteur());
				preparedStatement.setInt(7, document.getType());
				
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
	private void loadDatabase() {
		// Chargement du driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpjee_db", "root", "Gabydou@10");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
