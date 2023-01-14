package com.funquiz.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.funquiz.models.Location;

/**
 * IPRegistryAPIService to obtain location details
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class IPRegistryAPIService {

	/**
	 * To retrieve location details of the logging in user using IP
	 * 
	 * @param ip IP address of the user who is logging in
	 * @return Location object containing the IP, City, Postal Code, Latitude, Longitude and ISPName of the logging in user 
	 */
	public Location getLocation(String ip) {

		String city = null, latitude = null, longitude = null, postalCode = null , ispName = null;

		try {
			// Reference - https://mkyong.com/java/json-simple-example-read-and-write-json

			String url = "https://api.ipregistry.co/" + ip + "?key=tjgjb626suwgjj3f";

			HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

			// Optional default is GET
			httpClient.setRequestMethod("GET");

			// Add request header
			httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

			BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));

			StringBuilder response = new StringBuilder();
			String line;

			while ((line = in.readLine()) != null) {
				response.append(line);
			}

			Object responseObj = new JSONParser().parse(response.toString());

			JSONObject responseJsonObj = (JSONObject) responseObj;
			JSONObject locationObj = (JSONObject) responseJsonObj.get("location");
			
			if(locationObj.get("city") != null) {
				city = locationObj.get("city").toString();
				latitude = locationObj.get("latitude").toString();
				longitude = locationObj.get("longitude").toString();
				postalCode = locationObj.get("postal").toString();

				JSONObject connection = (JSONObject) responseJsonObj.get("connection");
				ispName = connection.get("organization").toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new Location(ip, city, postalCode, latitude, longitude, ispName);
	}
}


