package com.funquiz.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.funquiz.models.Game;
import com.funquiz.models.User;

/**
 * GameService to execute operations related to the games table in the database 
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class GameService implements ServiceInterface<Game> {

	@Override
	public boolean add(Game game) throws ClassNotFoundException, SQLException {
		int execution = 0;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "INSERT INTO `games`(`playerUName`, `quizSize`, `quizScore`, `quizResults`) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, game.getPlayerUName());
			ps.setInt(2, game.getQuizSize());
			ps.setFloat(3, game.getQuizScore());
			ps.setString(4, game.getQuizResults());

			execution = ps.executeUpdate();
		}
		return execution != 0;
	}

	@Override
	public boolean update(Game type) throws ClassNotFoundException, SQLException {
		return false;
	}

	@Override
	public boolean remove(Game type) throws ClassNotFoundException, SQLException {
		return false;
	}

	@Override
	public Game get(Game type) throws ClassNotFoundException, SQLException {
		return null;
	}

	@Override
	public List<Game> getAll() throws ClassNotFoundException, SQLException {
		return null;
	}

	/**
	 * To retrieve the previous quizes palyed by a specific user
	 * 
	 * @param user To obtain the username of the user who is requesting the previous quizs played by him/her
	 * @return List of Game objects which represents the previous quizes taken by a speicifc user
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public List<Game> getPlayerGames(User user) throws ClassNotFoundException, SQLException {
		List<Game> previousGames = null;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "SELECT `quizSize`, `quizResults`, `quizScore`, `playedDate` FROM `games` WHERE `playerUName` = ? ORDER By `playedDate` DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ResultSet rs = ps.executeQuery();

			previousGames = new ArrayList<Game>();
			while (rs.next()) {
				previousGames.add(new Game(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getTimestamp(4)));
			}
		}
		return previousGames;
	}

	/**
	 * To retrieve the top players of each quiz category (10, 20, 30, 40, 50, 75, etc.)
	 * 
	 * @return List of Game objects representing the top payers
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public List<Game> getLeaderBoard() throws ClassNotFoundException, SQLException {
		List<Game> topPlayers = null;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "SELECT `quizSize`, `playerUName`, MAX(`quizScore`) AS 'quizScore' FROM `games` GROUP BY `quizSize` ORDER BY `quizSize`  DESC LIMIT 10";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			topPlayers = new ArrayList<Game>();
			while (rs.next()) {
				topPlayers.add(new Game(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
			}
		}
		return topPlayers;
	}
}
