package com.tpjee.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpjee.models.Auteur;

public class AuteurDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/tpjee_db?useSSL=false";
	private String jdbcAuteurnom = "root";
	private String jdbcPassword = "Gabydou@10";

	private static final String INSERT_AUTEURS_SQL = "INSERT INTO auteurs" + "  (nom, prenom, nationalite) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_AUTEUR_BY_ID = "select id,nom,prenom,nationalite from auteurs where id =?";
	private static final String SELECT_ALL_AUTEURS = "select * from auteurs";
	private static final String DELETE_AUTEUR_SQL = "delete from auteurs where id = ?;";
	private static final String UPDATE_AUTEUR_SQL = "update auteurs set nom = ?,prenom= ?, nationalite =? where id = ?;";

	public AuteurDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcAuteurnom, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertAuteur(Auteur auteur) throws SQLException {
		System.out.println(INSERT_AUTEURS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTEURS_SQL)) {
			preparedStatement.setString(1, auteur.getNom());
			preparedStatement.setString(2, auteur.getPrenom());
			preparedStatement.setString(3, auteur.getNationalite());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Auteur selectAuteur(int id) {
		Auteur auteur = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTEUR_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String nationalite = rs.getString("nationalite");
				auteur = new Auteur();
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return auteur;
	}

	public List<Auteur> selectAllAuteurs() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Auteur> auteurs = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AUTEURS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String nationalite = rs.getString("nationalite");
				auteurs.add(new Auteur());
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return auteurs;
	}

	public boolean deleteAuteur(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_AUTEUR_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateAuteur(Auteur auteur) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_AUTEUR_SQL);) {
			statement.setString(1, auteur.getNom());
			statement.setString(2, auteur.getPrenom());
			statement.setString(3, auteur.getNationalite());
			statement.setInt(4, auteur.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
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
