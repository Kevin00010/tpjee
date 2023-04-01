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
import com.tpjee.models.MaisonEdition;
import com.tpjee.models.Utilisateur;

public class Noms {
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
			resultat = statement.executeQuery("SELECT nom, prenom FROM noms;");

			// Récupération des données
			while (resultat.next()) {
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");

				Utilisateur utilisateur = new Utilisateur();
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
			resultat = statement.executeQuery("SELECT id, nom, prenom, nationalite FROM Auteur;");

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
					.prepareStatement("INSERT INTO Auteur(nom, prenom, nationalite) VALUES(?, ?, ?);");
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
