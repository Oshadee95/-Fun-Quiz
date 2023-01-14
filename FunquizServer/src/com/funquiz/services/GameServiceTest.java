package com.funquiz.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.funquiz.models.Game;
import com.funquiz.models.User;

/**
 * Test class to execute and validate operations related to GameService
 * 
 * @author Oshadee Amarasinghe
 *
 */
@TestMethodOrder(OrderAnnotation.class)
class GameServiceTest {

	/**
	 * To test game creation 
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(1)
	public void gameAddTest() throws ClassNotFoundException, SQLException {
		GameService gameService = new GameService();
		Game game = new Game(10, "Oshadee", "1-Correct,2-Correct,3-Correct,4-Correct,5-Correct,6-Correct,7-Correct,8-Skipped,9-Incorrect,10-Correct,", 80);
		assertTrue(gameService.add(game));
	}
	
	/**
	 * To test specific game update 
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(2)
	public void gameUpdateTest() throws ClassNotFoundException, SQLException {
		GameService gameService = new GameService();
		assertFalse(gameService.update(null));
	}

	/**
	 * To test specific game retrieval 
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(3)
	public void gameGetTest() throws ClassNotFoundException, SQLException {
		GameService gameService = new GameService();
		assertNull(gameService.get(null));
	}
	
	/**
	 * To test specific game removal 
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(4)
	public void gameRemoveTest() throws ClassNotFoundException, SQLException {
		GameService gameService = new GameService();
		assertFalse(gameService.remove(null));
	}
	
	/**
	 * To test all game retrieval  
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(5)
	public void gameGetAllTest() throws ClassNotFoundException, SQLException {
		GameService gameService = new GameService();
		assertNull(gameService.getAll());
	}
	
	/**
	 * To test retrieval of games by a specific user 
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(6)
	public void gameGetByPlayerTest() throws ClassNotFoundException, SQLException {
		GameService gameService = new GameService();
		User user = new User("redhawk96");
		assertNotNull(gameService.getPlayerGames(user));
	}
	
	/**
	 * To test retrieval of leaderboard
	 * 
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	@Test
	@Order(7)
	public void gameGetLeaderBoardTest() throws ClassNotFoundException, SQLException {
		GameService gameService = new GameService();
		assertNotNull(gameService.getLeaderBoard());
	}
}