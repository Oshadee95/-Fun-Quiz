package com.funquiz.models;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * User class is used as as model class to transfer and retrieve data from server to client and vice versa
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class User implements Serializable {

	/**
	 * serialVersionUID of the class
	 */
	private static final long serialVersionUID = 608099854882736785L;
	
	/**
	 * userName, displayName, role, password, and status of the user
	 */
	private String userName, displayName, role, password, status;
	
	/**
	 * registeredDate of the user
	 */
	private Timestamp registeredDate;
	
	/**
	 * Default constructor for User
	 */
	public User() {}
	
	/**
	 * Constructor to get previous games by user and remove user from the database
	 * 
	 * @param userName Username of the user
	 */
	public User(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Constructor to authnticate (login) user
	 * 
	 * @param userName username of the registered user
	 * @param password of the registered user
	 */
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Constructor to register user to the database
	 * 
	 * @param userName username of the new user
	 * @param displayName Name of the of the new user
	 * @param password of the new user
	 */
	public User(String userName, String displayName, String password) {
		this.userName = userName;
		this.displayName = displayName;
		this.password = password;
	}
	
	/**
	 * To obtain the username of the user
	 * 
	 * @return Username of the user
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * To set username to user
	 * 
	 * @param username Username of the user
	 */
	public void setUserName(String username) {
		this.userName = username;
	}

	/**
	 * To obtain the display name of the user
	 * 
	 * @return Display name of the user
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * To set display name to the user
	 * 
	 * @param displayName Display name of the user
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * To obtain the role of the user
	 * 
	 * @return Role of the user (Admin or Player)
	 */
	public String getRole() {
		return role;
	}

	/**
	 * To set role to the user
	 * 
	 * @param role Role of the user (Admin or Player)
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * To obtain the timestamp of when the user was registered
	 * 
	 * @return Timestamp of when the user was registered
	 */
	public Timestamp getRegisteredDate() {
		return registeredDate;
	}

	/**
	 * To set timestamp to when registering user
	 * 
	 * @param registeredDate timestamp relating to when the user is being registered
	 */
	public void setRegisteredDate(Timestamp registeredDate) {
		this.registeredDate = registeredDate;
	}

	/**
	 * To obtain the unencrypted password of the user
	 * 
	 * @return Unencrypted password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * To set unencrypted password to the user
	 * 
	 * @param password Unencrypted password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * To obtain the status of the user
	 * 
	 * @return Status to the user (Active or Inactive)
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * To set status to the user
	 * 
	 * @param status Status to the user (Active or Inactive)
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
