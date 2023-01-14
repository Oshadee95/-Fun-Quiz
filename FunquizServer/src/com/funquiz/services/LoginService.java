package com.funquiz.services;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.funquiz.models.User;
import com.funquiz.utils.EncryptionUtil;

/**
 * LoginService to execute operations related to user athentication 
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class LoginService {

	/**
	 * To validate provided username and password against database entries
	 * 
	 * @param user To obtain the username and password to authenticate credentails
	 * @return Whether user was authencated (User) or not (null)
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public User login(User user) throws ClassNotFoundException, SQLException {
		User authUser = null;
		if (DBService.getDBConnection() != null) {
			try {
				Connection con = DBService.getDBConnection();
				String sql = "SELECT `userName`, `displayName`, `role` as count FROM `users` WHERE `userName` = ? AND `password` = ? AND `status` = 'ACTIVE' LIMIT 1";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getUserName());
				ps.setString(2, EncryptionUtil.getEncryptedPassword(user.getPassword()));

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					authUser = new User();
					authUser.setUserName(rs.getString(1));
					authUser.setDisplayName(rs.getString(2));
					authUser.setRole(rs.getString(3));
					return authUser;
				}
			} catch (NoSuchAlgorithmException | NoSuchProviderException | SQLException e) {
				e.printStackTrace();
			}
		}
		return authUser;
	}

	/**
	 * To check the availability of a selected username when registering to the system
	 * 
	 * @param username To check the the availability of the selected username 
	 * @return Whether username is available (true) or not (false)
	 * @throws ClassNotFoundException When JVM tries to load a particular class and the specified class cannot be found in the classpath
	 * @throws SQLException When JDBC encounters an error during an interaction with a data source
	 */
	public boolean isUserNameAvailable(String username) throws ClassNotFoundException, SQLException {
		boolean isUserNameAvailable = true;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "SELECT count(*) as count FROM `users` WHERE `userName` = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return rs.getInt(1) == 0;
			}
		}
		return isUserNameAvailable;
	}
}
