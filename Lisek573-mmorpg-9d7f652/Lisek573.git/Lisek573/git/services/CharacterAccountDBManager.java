package Lisek573.git.services;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import Lisek573.git.mainthings.Character;

public class CharacterAccountDBManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement addCharacterToAccountStmt;
	private PreparedStatement deleteAllAccountCharacterStmt;
	private PreparedStatement deleteAllCharacterFromAccountStmt;
	private PreparedStatement getCharacterAccountStmt;

	public CharacterAccountDBManager() {
		try {
			Properties props = new Properties();

			try {
				props.load(ClassLoader
						.getSystemResourceAsStream("Lisek573/git/services/jdbc.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			conn = DriverManager.getConnection(props.getProperty("url"));

			stmt = conn.createStatement();
			boolean accountTableExists = false;

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			while (rs.next()) {
				if ("CharacterAccount".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					accountTableExists = true;
					break;
				}
			}

			if (!accountTableExists) {
				stmt.executeUpdate("CREATE TABLE characterAccount(account_id int, character_id int, CONSTRAINT account_id_fk FOREIGN KEY (account_id) REFERENCES account (id), CONSTRAINT character_id_fk FOREIGN KEY (character_id) REFERENCES character (id))");
			}

			addCharacterToAccountStmt = conn
					.prepareStatement("INSERT INTO characterAccount (account_id, character_id) VALUES (?, ?)");

			deleteAllAccountCharacterStmt = conn
					.prepareStatement("DELETE FROM characterAccount WHERE account_id = ?");

			deleteAllCharacterFromAccountStmt = conn
					.prepareStatement("DELETE FROM characterAccount");

			getCharacterAccountStmt = conn
					.prepareStatement("SELECT Character.name,Character.level,Character.serial FROM Character, AccountCharacter WHERE account_id = ? and character_id = Character.id");

		} catch (java.sql.SQLException e) {

			e.printStackTrace();
		}
	}

	public void addCharacterToAccount(List<Integer> listAccountId,
			List<Integer> listCharacterId) throws java.sql.SQLException {
		try {
			for (Integer accountID : listAccountId) {
				for (Integer characterID : listCharacterId) {
					addCharacterToAccountStmt.setInt(1, accountID);
					addCharacterToAccountStmt.setInt(2, characterID);
					addCharacterToAccountStmt.executeUpdate();
				}
			}
		} catch (java.sql.SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteAllAccountCharacter(List<Integer> listAccountId) throws java.sql.SQLException {
		try {
			for (Integer accountID : listAccountId) {
				deleteAllAccountCharacterStmt.setInt(1, accountID);
				deleteAllAccountCharacterStmt.executeUpdate();
			}
		} catch (java.sql.SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteAllCharacterFromAccount() throws java.sql.SQLException {
		try {
			deleteAllCharacterFromAccountStmt.executeUpdate();
		} catch (java.sql.SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Character> getAccountCharacter(List<Integer> listAccountId) throws java.sql.SQLException {
		List<Character> Characters = new ArrayList<Character>();
		try {
			for (Integer accountID : listAccountId) {
				getCharacterAccountStmt.setInt(1, accountID);
				ResultSet rs = getCharacterAccountStmt.executeQuery();
				while (rs.next()) {
				
							
					
					Characters.add(new Character(rs.getString("name"), null, rs
							.getInt("level"), rs.getFloat("serial")));
				}
			}
		} catch (java.sql.SQLException e) {

			e.printStackTrace();
		}
		return Characters;
	}

}
