package Lisek573.git.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Lisek573.git.CharacterJobs;
import Lisek573.git.Character;

public class CharacterDBManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement addCharacterStmt;
	private PreparedStatement getCharacterStmt;
	private PreparedStatement dropCharacterStmt;
	private PreparedStatement deleteAllCharactersStmt;
	private PreparedStatement findCharacterByNameStmt;
	private PreparedStatement findCharacterBySerialStmt;
	public CharacterDBManager() {

		try {

			boolean productTableExists = false;
			conn = DriverManager
					.getConnection("jdbc:hsqldb:hsql://localhost/workdb");

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
			stmt = conn.createStatement();

			while (rs.next()) {
				if ("Character".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					productTableExists = true;
					break;
				}
			}

			if (!productTableExists) {

				stmt.executeUpdate(""
						+ "CREATE TABLE Character("
						+ "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
						+ "name varchar(20)," + "level integer," + "serial float,"
						+ "" + ")");

			}

			addCharacterStmt = conn.prepareStatement(""
					+ "INSERT INTO Character (name,level,serial) VALUES (?,?,?)"
					+ "");

			getCharacterStmt = conn.prepareStatement("" + "SELECT * FROM Character"
					+ "");

			dropCharacterStmt = conn.prepareStatement("" + "DROP TABLE Character"
					+ "");

			deleteAllCharactersStmt = conn
					.prepareStatement("DELETE FROM Character");

			findCharacterByNameStmt = conn
					.prepareStatement("SELECT id FROM Character WHERE name = ?");

			findCharacterBySerialStmt = conn
					.prepareStatement("SELECT id FROM Character WHERE serial = ?");

			conn
					.prepareStatement("SELECT id FROM Character WHERE level = ?");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void addCharacter(Character p) throws java.sql.SQLException {
		addCharacterStmt.setString(1, p.getName().toString());
		addCharacterStmt.setInt(2, p.getLevel());
		addCharacterStmt.setFloat(3, p.getSerial());
		addCharacterStmt.executeUpdate();

	}

	public void droptableproduct() throws java.sql.SQLException {

		dropCharacterStmt.executeUpdate();
	}

	public List<Integer> findCharacterByName(CharacterJobs Job)
			throws java.sql.SQLException {
		try {
			List<Integer> result = new ArrayList<Integer>();
			findCharacterByNameStmt.setString(1, Job.toString());
			ResultSet rs = findCharacterByNameStmt.executeQuery();
			while (rs.next())
				result.add(rs.getInt("ID"));
			return result;
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Integer> findCharacterBySerial(float Serial)
			throws java.sql.SQLException {
		try {
			List<Integer> result = new ArrayList<Integer>();
			findCharacterBySerialStmt.setFloat(1, Serial);
			ResultSet rs = findCharacterBySerialStmt.executeQuery();
			while (rs.next())
				result.add(rs.getInt("ID"));
			return result;
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Character> getAllCharacters() throws java.sql.SQLException {
		List<Character> products = new ArrayList<Character>();
		try {
			ResultSet rs = getCharacterStmt.executeQuery();
			while (rs.next()) {

				products.add(new Character(rs.getString("name"), null, rs
						.getInt("level"), rs.getFloat("serial")));

			}

		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public void printCharacterWithCondition(List<Character> listCharacters,
			Lisek573.git.services.Condition condition) {
		for (Character p : listCharacters) {
			if (condition.getCondition(p)) {
				System.out.println("Name: " + p.getName() + "\tJob: "
						+ p.getJob() + "\tLevel: " + p.getLevel());
			}
		}
	}

	public void deleteAllCharacters()
	{
		try 
		{
			deleteAllCharactersStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}
