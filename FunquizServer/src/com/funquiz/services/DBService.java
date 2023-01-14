package com.funquiz.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBService to establish connection to the database 
 * 
 * @author Oshadee Amarasinghe
 *
 */
public final class DBService {

	private static java.sql.Connection conn;
	
	/**
	 * Singleton design pattern is implemented to avoid creation of multiple instances of DBService
	 */
	private DBService() {}

	/**
	 * To retrieve the database connection
	 * 
	 * @return boolean To indicate whether connection to database is established (true) or not (false)
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		if (conn == null) {
			DBService.setConnection();
		}
		return conn;
	}

	/**
	 * To establish connection to the database using predefined credentials
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	private static void setConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/";
		String dbname = "fun_quiz";
		String ssl = "?useSSL=false";
		String username = "root";
		String password = "";

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url + dbname + ssl, username, password);
	}
}
