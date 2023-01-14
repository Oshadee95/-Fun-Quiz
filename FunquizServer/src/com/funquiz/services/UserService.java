package com.funquiz.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.funquiz.models.User;
import com.funquiz.utils.EncryptionUtil;

/**
 * UserService to execute operations related to the users table in the database 
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class UserService implements ServiceInterface<User> {

	@Override
	public boolean add(User user) throws ClassNotFoundException, SQLException {
		int execution = 0;
		if (DBService.getDBConnection() != null) {
			try {
				Connection con = DBService.getDBConnection();
				String sql = "INSERT INTO `users`(`userName`, `displayName`, `password`) VALUES (?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getUserName());
				ps.setString(2, user.getDisplayName());
				ps.setString(3, EncryptionUtil.getEncryptedPassword(user.getPassword()));
				
				execution = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return execution != 0;
	}

	@Override
	public boolean update(User user) throws ClassNotFoundException, SQLException {
		int execution = 0;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			if(user.getPassword() != null) {
				try {
					String sql = "UPDATE `users` SET `displayName` = ?, `password` = ? WHERE userName = ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, user.getDisplayName());
					ps.setString(2, EncryptionUtil.getEncryptedPassword(user.getPassword()));
					ps.setString(3, user.getUserName());
					execution = ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			} else {
				String sql = "UPDATE `users` SET `displayName` = ? WHERE userName = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getDisplayName());
				ps.setString(2, user.getUserName());
				execution = ps.executeUpdate();
			}
		}
		return execution != 0;
	}

	@Override
	public boolean remove(User user) throws ClassNotFoundException, SQLException {
		int execution = 0;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "DELETE FROM `users` WHERE `userName` = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserName());
		
			execution = ps.executeUpdate();
		}
		return execution != 0;
	}

	@Override
	public User get(User user) throws ClassNotFoundException, SQLException {
		User dbUser = null;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "SELECT `userName`, `displayName`, `role`, `registeredDate`, `status` FROM `users` WHERE `userName` = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ResultSet rs = ps.executeQuery();
			
			dbUser = new User();
			while(rs.next()) {
				dbUser.setUserName(rs.getString(1));
				dbUser.setDisplayName(rs.getString(2));
				dbUser.setRole(rs.getString(3));
				dbUser.setRegisteredDate(rs.getTimestamp(4));
				dbUser.setStatus(rs.getString(5));
			}
		}
		return dbUser;
	}

	@Override
	public List<User> getAll() throws ClassNotFoundException, SQLException {
		List<User> dbUserList = null;
		if (DBService.getDBConnection() != null) {
			Connection con = DBService.getDBConnection();
			String sql = "SELECT `userName`, `displayName`, `role`, `registeredDate`, `status` FROM `users`";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			dbUserList = new ArrayList<User>();
			while(rs.next()) {
				User dbUser = new User();
				dbUser.setUserName(rs.getString(1));
				dbUser.setDisplayName(rs.getString(2));
				dbUser.setRole(rs.getString(3));
				dbUser.setRegisteredDate(rs.getTimestamp(4));
				dbUser.setStatus(rs.getString(5));
				dbUserList.add(dbUser);
			}
		}
		return dbUserList;
	}
}
