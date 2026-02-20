package util;

import java.sql.*;

public class DBManager {

	private static Connection connection;

	public static void connect() {
		try {
			Class.forName(ConfigReader.get("db.driver"));
			connection = DriverManager.getConnection(
					ConfigReader.get("db.url"),
					ConfigReader.get("db.username"),
					ConfigReader.get("db.password")
					);
			System.out.println("DB Connected Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet executeQuery(String query) {
		try {
			Statement statement = connection.createStatement();
			return statement.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
				System.out.println("DB Connection Closed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}