package com.funquiz.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import com.funquiz.models.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Test class to execute and validate operations related to UserService
 * 
 * @author Oshadee Amarasinghe
 *
 */
@TestMethodOrder(OrderAnnotation.class)
class UserServiceTest {

	/**
	 * To test user creation
	 * 
	 * @throws ClassNotFound Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(1)
	void userAddTest() throws ClassNotFoundException, SQLException {
		UserService userService = new UserService();
		User user = new User("oshee95", "Oshadee", "123456");
		assertTrue(userService.add(user));
	}

	/**
	 * To test specific user update
	 * 
	 * @throws ClassNotFound Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(2)
	void userUpdateTest() throws ClassNotFoundException, SQLException {
		UserService userService = new UserService();
		User user = new User("oshee95");
		user.setDisplayName("Oshadee Amarasinghe");
		assertTrue(userService.update(user));
	}

	/**
	 * To test specific user retrieval 
	 * 
	 * @throws ClassNotFound Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(3)
	void userGetTest() throws ClassNotFoundException, SQLException {
		UserService userService = new UserService();
		User user = new User("oshee95");
		User dbUser = userService.get(user);
		assertEquals("oshee95", dbUser.getUserName());
		assertEquals("Oshadee Amarasinghe", dbUser.getDisplayName());
		assertEquals("PLAYER", dbUser.getRole());
		assertEquals("ACTIVE", dbUser.getStatus());
	}

	/**
	 * To test specific user removal
	 * 
	 * @throws ClassNotFound Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(4)
	void userRemoveTest() throws ClassNotFoundException, SQLException {
		UserService userService = new UserService();
		User user = new User("oshee95");
		assertTrue(userService.remove(user));
	}

	/**
	 * To test all user retrieval
	 * 
	 * @throws ClassNotFound Exception When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(5)
	void userGetAllTest() throws ClassNotFoundException, SQLException {
		UserService userService = new UserService();
		List<User> dbUserList = userService.getAll();
		assertEquals(2, dbUserList.size());
	}
}
