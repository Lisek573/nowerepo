package Lisek573.git.services;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import Lisek573.git.Character;

public class CharacterAccountDBManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement addProductToClientStmt;
	private PreparedStatement deleteAllClientProductStmt;
	private PreparedStatement deleteAllProductFromClientStmt;
	private PreparedStatement getProductClientStmt;

	public CharacterAccountDBManager() throws java.sql.SQLException {
		try {
			Properties props = new Properties();

			try {
				props.load(ClassLoader
						.getSystemResourceAsStream("com/sklep/jdbc.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			conn = DriverManager.getConnection(props.getProperty("url"));

			stmt = conn.createStatement();
			boolean clientTableExists = false;

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			while (rs.next()) {
				if ("CharacterAccount".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					clientTableExists = true;
					break;
				}
			}

			if (!clientTableExists) {
				stmt.executeUpdate("CREATE TABLE characterAccount(account_id int, product_id int, CONSTRAINT client_id_fk FOREIGN KEY (client_id) REFERENCES client (id), CONSTRAINT character_id_fk FOREIGN KEY (product_id) REFERENCES product (id))");
			}

			addProductToClientStmt = conn
					.prepareStatement("INSERT INTO characterAccount (client_id, product_id) VALUES (?, ?)");

			deleteAllClientProductStmt = conn
					.prepareStatement("DELETE FROM characterAccount WHERE client_id = ?");

			deleteAllProductFromClientStmt = conn
					.prepareStatement("DELETE FROM characterAccount");

			getProductClientStmt = conn
					.prepareStatement("SELECT Character.name,Character.level,Character.serial FROM Character, ClientProduct WHERE client_id = ? and product_id = Product.id");

		} catch (java.sql.SQLException e) {

			e.printStackTrace();
		}
	}

	public void addProductToClient(List<Integer> listClientId,
			List<Integer> listProductId) throws java.sql.SQLException {
		try {
			for (Integer clientID : listClientId) {
				for (Integer productID : listProductId) {
					addProductToClientStmt.setInt(1, clientID);
					addProductToClientStmt.setInt(2, productID);
					addProductToClientStmt.executeUpdate();
				}
			}
		} catch (java.sql.SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteAllClientProduct(List<Integer> listClientId) throws java.sql.SQLException {
		try {
			for (Integer clientID : listClientId) {
				deleteAllClientProductStmt.setInt(1, clientID);
				deleteAllClientProductStmt.executeUpdate();
			}
		} catch (java.sql.SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteAllProductFromClient() throws java.sql.SQLException {
		try {
			deleteAllProductFromClientStmt.executeUpdate();
		} catch (java.sql.SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Character> getClientProduct(List<Integer> listClientId) throws java.sql.SQLException {
		List<Character> Characters = new ArrayList<Character>();
		try {
			for (Integer clientID : listClientId) {
				getProductClientStmt.setInt(1, clientID);
				ResultSet rs = getProductClientStmt.executeQuery();
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
