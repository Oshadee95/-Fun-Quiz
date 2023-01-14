package com.funquiz.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.funquiz.models.User;

/**
 * Test class to execute and validate operations related to LoginService
 * 
 * @author Oshadee Amarasinghe
 *
 */
class LoginServiceTest {

	/**
	 * To test valid login
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	void validLoginTest() throws ClassNotFoundException, SQLException {
		LoginService loginService = new LoginService();
		User user = new User("admin", "123456");
		User dbUser = loginService.login(user);
		assertEquals("admin", dbUser.getUserName());
		assertEquals("ADMIN", dbUser.getDisplayName());
		assertEquals("ADMIN", dbUser.getRole());
	}
	
	/**
	 * To test invalid login
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	void inValidLoginTest() throws ClassNotFoundException, SQLException {
		LoginService loginService = new LoginService();
		User user = new User("oshadee3", "12345");
		assertNull(loginService.login(user));
	}
	
	/**
	 * To test available username
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	void availableUsernameTest() throws ClassNotFoundException, SQLException {
		LoginService loginService = new LoginService();
		String username = "oshadee3";
		assertTrue(loginService.isUserNameAvailable(username));
	}
	
	/**
	 * To test unavailable username
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	void unavailableUsernameTest() throws ClassNotFoundException, SQLException {
		LoginService loginService = new LoginService();
		String username = "oshadee";
		assertFalse(loginService.isUserNameAvailable(username));
	}
}
