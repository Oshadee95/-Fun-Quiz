package com.funquiz.services;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

/**
 * Test class to execute and validate operations related to DBService
 * 
 * @author Oshadee Amarasinghe
 *
 */
class DBServiceTest {

	/**
	 * To test db connection
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	void dbConnectionTest() throws ClassNotFoundException, SQLException {
		Connection dbConnection = DBService.getDBConnection();
		assertNotNull(dbConnection);
	}
}
