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

public class AuteurRepo {
	private Connection connexion;

	private String jdbcURL = "jdbc:mysql://localhost:3306/tpjee_db?useSSL=false";
	private String jdbcAuteurnom = "root";
	private String jdbcPassword = "Gabydou@10";

	private static final String INSERT_AUTEUR_SQL = "INSERT INTO auteurs" + "  (nom, prenom, nationalite) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_AUTEUR_BY_ID = "select id,nom,prenom,nationalite from auteurs where id =?";
	private static final String SELECT_ALL_AUTEURS = "select * from auteurs ORDER BY auteurs.id";
	private static final String SELECT_ALL_NATIONALITIES = "select designation from nationalities";
	private static final String DELETE_AUTEUR_SQL = "delete from auteurs where id = ?;";
	private static final String UPDATE_AUTEUR_SQL = "update auteurs set nom = ?,prenom= ?, nationalite =? where id = ?;";

	public List<Auteur> listAuteurs() {
		List<Auteur> auteurs = new ArrayList<Auteur>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();

			// Exécution de la requête
			resultat = statement.executeQuery(SELECT_ALL_AUTEURS);

			// Récupération des données
			while (resultat.next()) {
				Integer id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String nationalite = resultat.getString("nationalite");
				auteurs.add(new Auteur(id, nom, prenom, nationalite));
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

	public List<String> listNationalites() {
		List<String> nationalites = new ArrayList<String>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();

			// Exécution de la requête
			resultat = statement.executeQuery(SELECT_ALL_NATIONALITIES);

			// Récupération des données
			while (resultat.next()) {
				
				String designation = resultat.getString("designation");
				
				nationalites.add(designation);
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

		return nationalites;
	}
	//insert an author in the database
	public void insertAuteur(Auteur auteur) {

		loadDatabase();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(INSERT_AUTEUR_SQL);
			preparedStatement.setString(1, auteur.getNom());
			preparedStatement.setString(2, auteur.getPrenom());
			preparedStatement.setString(3, auteur.getNationalite());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	//find one author from database
	public Auteur selectAuteur(int id) {
		Auteur auteur = null;
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(SELECT_AUTEUR_BY_ID);
			preparedStatement.setInt(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String nationalite = rs.getString("nationalite");
				auteur = new Auteur(id, nom, prenom, nationalite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return auteur;
	}

	public boolean updateAuteur(Auteur auteur) throws SQLException {
		loadDatabase();
		boolean rowUpdated;
		try {
			PreparedStatement statement = connexion.prepareStatement(UPDATE_AUTEUR_SQL);
			statement.setString(1, auteur.getNom());
			statement.setString(2, auteur.getPrenom());
			statement.setString(3, auteur.getNationalite());
			statement.setInt(4, auteur.getId());

			rowUpdated = statement.executeUpdate() > 0;
		} finally {

		}
		return rowUpdated;
	}

	public boolean deleteAuteur(int id) throws SQLException {

		loadDatabase();
		boolean rowDeleted;
		try {
			PreparedStatement statement = connexion.prepareStatement(DELETE_AUTEUR_SQL);
			statement.setInt(1, id);
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
			connexion = DriverManager.getConnection(jdbcURL, jdbcAuteurnom, jdbcPassword);
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
