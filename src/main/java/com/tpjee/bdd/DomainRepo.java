package com.tpjee.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tpjee.models.Domain;

public class DomainRepo {
	private Connection connexion;

	private String jdbcURL = "jdbc:mysql://localhost:3306/tpjee_db";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Gabydou@10";

	private static final String INSERT_DOMAIN_SQL = "INSERT INTO domaines" + "  (designation) VALUES "
			+ " (?);";

	private static final String SELECT_DOMAIN_BY_ID = "select id,designation from domaines where id =?";
	private static final String SELECT_ALL_DOMAINS = "select * from domaines";
	private static final String DELETE_DOMAIN_SQL = "delete from domaines where id = ?;";
	private static final String UPDATE_DOMAIN_SQL = "update domaines set designation = ? where id = ?;";

	public List<Domain> listDomains() {
		List<Domain> domains = new ArrayList<Domain>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();

			// Exécution de la requête
			resultat = statement.executeQuery(SELECT_ALL_DOMAINS);

			// Récupération des données
			while (resultat.next()) {
				Integer id = resultat.getInt("id");
				String designation = resultat.getString("designation");
				
				domains.add(new Domain(id, designation));
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

		return domains;
	}

	public void insertDomain(Domain domain) {

		loadDatabase();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(INSERT_DOMAIN_SQL);
			preparedStatement.setString(1, domain.getDesignation());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Domain selectDomain(int id) {
		Domain domain = null;
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(SELECT_DOMAIN_BY_ID);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String designation = rs.getString("designation");
				
				domain = new Domain(id, designation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return domain;
	}

	public boolean updateDomain(Domain domain) throws SQLException {
		loadDatabase();
		boolean rowUpdated;
		try {
			PreparedStatement statement = connexion.prepareStatement(UPDATE_DOMAIN_SQL);
			statement.setInt(2, domain.getId());
			statement.setString(1, domain.getDesignation());
			
			rowUpdated = statement.executeUpdate() > 0;
		} finally {

		}
		return rowUpdated;
	}

	public boolean deleteDomain(int id) throws SQLException {

		loadDatabase();
		boolean rowDeleted;
		try {
			PreparedStatement statement = connexion.prepareStatement(DELETE_DOMAIN_SQL);
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
