package com.funquiz.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Encryption class to secure values in plain text
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class EncryptionUtil {
	
	/**
	 * To encrypt password using MD5 algorithm
	 * 
	 * @param passwordToBeEncrypted Password that needs to be encrypted
	 * @return password encrypted using MD5 algorithm
	 * @throws NoSuchAlgorithmException When a particular cryptographic algorithm is requested but is not available in the environment
	 * @throws NoSuchProviderException When a particular security provider is requested but is not available in the environment
	 */
	public static String getEncryptedPassword(String passwordToBeEncrypted) throws NoSuchAlgorithmException, NoSuchProviderException {
		String generatedPassword = null;
		// Create MessageDigest instance for MD5
		MessageDigest md = MessageDigest.getInstance("MD5");
		// Add password bytes to digest
		md.update(passwordToBeEncrypted.getBytes());
		// Get the hash's bytes
		byte[] bytes = md.digest();
		// This bytes[] has bytes in decimal format;
		// Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		// Gets complete hashed password in hex format
		generatedPassword = sb.toString();
		
		return generatedPassword;
    }
}