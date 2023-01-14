package com.funquiz.services;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */

public class IpifyAPIService {

	public static String getIP() throws MalformedURLException, IOException {
		@SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipify.org").openStream(), "UTF-8").useDelimiter("\\A");
		return s.next();
	}
}


