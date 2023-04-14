package com.tpjee.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tpjee.models.Utilisateur;

public class UtilisateurRepo {
	private Connection connexion;

	private String jdbcURL = "jdbc:mysql://localhost:3306/tpjee_db?useSSL=false";
	private String jdbcUtilisateurnom = "root";
	private String jdbcPassword = "Gabydou@10";

	private static final String INSERT_UTILISATEUR_SQL = "INSERT INTO utilisateurs" + "  (nom, prenom, email, password) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_UTILISATEUR_BY_ID = "select id,nom,prenom,email, password from utilisateurs where id =?";
	private static final String SELECT_ALL_UTILISATEURS = "select id, nom, prenom, email from utilisateurs";
	private static final String LOGIN_CHECK = "select id, nom, prenom, email from utilisateurs where email = ? AND password = ?";
	private static final String DELETE_UTILISATEUR_SQL = "delete from utilisateurs where id = ?;";
	private static final String UPDATE_UTILISATEUR_SQL = "update utilisateurs set nom = ?,prenom= ?, email =? where id = ?;";

	public List<Utilisateur> listUtilisateurs() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();

			// Exécution de la requête
			resultat = statement.executeQuery(SELECT_ALL_UTILISATEURS);

			// Récupération des données
			while (resultat.next()) {
				Integer id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String email = resultat.getString("email");
				utilisateurs.add(new Utilisateur(id, nom, prenom, email));
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

	public Utilisateur loginCheck(String username, String pwd) {
		Utilisateur utilisateur = null;
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(LOGIN_CHECK);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, pwd);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				utilisateur = new Utilisateur(id, nom, prenom, email);
				System.out.println(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	public void insertUtilisateur(Utilisateur utilisateur) {

		loadDatabase();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(INSERT_UTILISATEUR_SQL);
			preparedStatement.setString(1, utilisateur.getNom());
			preparedStatement.setString(2, utilisateur.getPrenom());
			preparedStatement.setString(3, utilisateur.getEmail());
			preparedStatement.setString(4, utilisateur.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Utilisateur selectUtilisateur(int id) {
		Utilisateur utilisateur = null;
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(SELECT_UTILISATEUR_BY_ID);
			preparedStatement.setInt(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String password = rs.getString("password");
				utilisateur = new Utilisateur(id, nom, prenom, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	public boolean updateUtilisateur(Utilisateur utilisateur) throws SQLException {
		loadDatabase();
		boolean rowUpdated;
		try {
			PreparedStatement statement = connexion.prepareStatement(UPDATE_UTILISATEUR_SQL);
			statement.setString(1, utilisateur.getNom());
			statement.setString(2, utilisateur.getPrenom());
			statement.setString(3, utilisateur.getEmail());
			statement.setString(4, utilisateur.getPassword());
			statement.setInt(5, utilisateur.getId());

			rowUpdated = statement.executeUpdate() > 0;
		} finally {

		}
		return rowUpdated;
	}

	public boolean deleteUtilisateur(int id) throws SQLException {

		loadDatabase();
		boolean rowDeleted;
		try {
			PreparedStatement statement = connexion.prepareStatement(DELETE_UTILISATEUR_SQL);
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
			connexion = DriverManager.getConnection(jdbcURL, jdbcUtilisateurnom, jdbcPassword);
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
