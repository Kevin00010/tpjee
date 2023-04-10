package com.tpjee.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tpjee.models.MaisonEdition;

public class MaisonEditionRepo {
	private Connection connexion;

	private String jdbcURL = "jdbc:mysql://localhost:3306/tpjee_db";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Gabydou@10";

	private static final String INSERT_MAISON_EDITION_SQL = "INSERT INTO maison_editions" + "  (nomMaison) VALUES "
			+ " (?);";

	private static final String SELECT_MAISON_EDITION_BY_ID = "select id, nomMaison from maison_editions where id =?";
	private static final String SELECT_ALL_MAISON_EDITIONS = "select * from maison_editions";
	private static final String DELETE_MAISON_EDITION_SQL = "delete from maison_editions where id = ?;";
	private static final String UPDATE_MAISON_EDITION_SQL = "update maison_editions set nomMaison = ? where id = ?;";

	public List<MaisonEdition> listMaisonEditions() {
		List<MaisonEdition> maisonEditions = new ArrayList<MaisonEdition>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();

			// Exécution de la requête
			resultat = statement.executeQuery(SELECT_ALL_MAISON_EDITIONS);

			// Récupération des données
			while (resultat.next()) {
				Integer id = resultat.getInt("id");
				String nomMaison = resultat.getString("nomMaison");
				
				maisonEditions.add(new MaisonEdition(id, nomMaison));
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

	public void insertMaisonEdition(MaisonEdition maisonEdition) {

		loadDatabase();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(INSERT_MAISON_EDITION_SQL);
			preparedStatement.setString(1, maisonEdition.getNomMaison());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public MaisonEdition selectMaisonEdition(int id) {
		MaisonEdition maisonEdition = null;
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(SELECT_MAISON_EDITION_BY_ID);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nomMaison = rs.getString("nomMaison");
				
				maisonEdition = new MaisonEdition(id, nomMaison);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maisonEdition;
	}

	public boolean updateMaisonEdition(MaisonEdition maisonEdition) throws SQLException {
		loadDatabase();
		boolean rowUpdated;
		try {
			PreparedStatement statement = connexion.prepareStatement(UPDATE_MAISON_EDITION_SQL);
			statement.setInt(2, maisonEdition.getId());
			statement.setString(1, maisonEdition.getNomMaison());
			
			rowUpdated = statement.executeUpdate() > 0;
		} finally {

		}
		return rowUpdated;
	}

	public boolean deleteMaisonEdition(int id) throws SQLException {

		loadDatabase();
		boolean rowDeleted;
		try {
			PreparedStatement statement = connexion.prepareStatement(DELETE_MAISON_EDITION_SQL);
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} finally {
		}
		return rowDeleted;
	}

	private void loadDatabase() {
		// Chargement du driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			connexion = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
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
