package com.tpjee.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tpjee.models.TypeDocument;

public class TypeDocumentRepo {
	private Connection connexion;

	private String jdbcURL = "jdbc:mysql://localhost:3306/tpjee_db";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Gabydou@10";

	private static final String INSERT_TYPEDOCUMENT_SQL = "INSERT INTO type_documents" + "  (designation) VALUES "
			+ " (?);";

	private static final String SELECT_TYPEDOCUMENT_BY_ID = "select id,designation from type_documents where id =?";
	private static final String SELECT_ALL_TYPEDOCUMENTS = "select * from type_documents";
	private static final String DELETE_TYPEDOCUMENT_SQL = "delete from type_documents where id = ?;";
	private static final String UPDATE_TYPEDOCUMENT_SQL = "update type_documents set designation = ? where id = ?;";

	public List<TypeDocument> listTypeDocuments() {
		List<TypeDocument> typeDocuments = new ArrayList<TypeDocument>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();

			// Exécution de la requête
			resultat = statement.executeQuery(SELECT_ALL_TYPEDOCUMENTS);

			// Récupération des données
			while (resultat.next()) {
				Integer id = resultat.getInt("id");
				String designation = resultat.getString("designation");
				
				typeDocuments.add(new TypeDocument(id, designation));
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

	public void insertTypeDocument(TypeDocument typeDocument) {

		loadDatabase();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(INSERT_TYPEDOCUMENT_SQL);
			preparedStatement.setString(1, typeDocument.getDesignation());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public TypeDocument selectTypeDocument(int id) {
		TypeDocument typeDocument = null;
		loadDatabase();

		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connexion.prepareStatement(SELECT_TYPEDOCUMENT_BY_ID);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String designation = rs.getString("designation");
				
				typeDocument = new TypeDocument(id, designation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typeDocument;
	}

	public boolean updateTypeDocument(TypeDocument typeDocument) throws SQLException {
		loadDatabase();
		boolean rowUpdated;
		try {
			PreparedStatement statement = connexion.prepareStatement(UPDATE_TYPEDOCUMENT_SQL);
			statement.setInt(2, typeDocument.getId());
			statement.setString(1, typeDocument.getDesignation());
			
			

			rowUpdated = statement.executeUpdate() > 0;
		} finally {

		}
		return rowUpdated;
	}

	public boolean deleteTypeDocument(int id) throws SQLException {

		loadDatabase();
		boolean rowDeleted;
		try {
			PreparedStatement statement = connexion.prepareStatement(DELETE_TYPEDOCUMENT_SQL);
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
