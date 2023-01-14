package com.funquiz.models;

import java.io.Serializable;

/**
 * Location class is used as as model class to transfer and retrieve data from server to client and vice versa
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class Location implements Serializable {

	/**
	 * serialVersionUID of the class
	 */
	private static final long serialVersionUID = -4448925827235110083L;
	
	/**
	 * ip, city, postalCode, latitude, longitude and ispName of the user
	 */
	private String ip, city, postalCode, latitude, longitude, ispName;

	/**
	 * Constructor to retrieve the location details of a user
	 * 
	 * @param ip IP address of the user
	 * @param city City of which the user is located in
	 * @param postalCode Postal code of the located city
	 * @param latitude Latitude points of the located address
	 * @param longitude Longitude points of the located address
	 * @param ispName Internet service provider's name of which the user in using to access the application
	 */
	public Location(String ip, String city, String postalCode, String latitude, String longitude, String ispName) {
		this.ip = ip;
		this.city = city;
		this.postalCode = postalCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ispName = ispName;
	}

	/**
	 * To obtain the IP address of the user
	 * 
	 * @return IP address of the user
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * To obtain the located city of the user
	 * 
	 * @return Located city of the user
	 */
	public String getCity() {
		return city;
	}

	/**
	 * To obtain the postal code of the located city
	 * 
	 * @return Postal code of the located city
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * To obtain the latitude points of the located address
	 * 
	 * @return Latitude points of the located address
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * To obtain the longitude points of the located address
	 * 
	 * @return Longitude points of the located address
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * To obtain the Internet service provider's name
	 * 
	 * @return Internet service provider's name of which the user in using to access the application
	 */
	public String getIspName() {
		return ispName;
	}

	/**
	 * 
	 * To print user's location details in a readable format
	 */
	public void displayDetails() {
		System.out.println("User located by IP - " + this.ip);
		System.out.println("Physically location at " + this.city + ", " + this.postalCode);
		System.out.println("Geo-points identified as latitude - " + this.latitude + ", longitude - " + this.longitude);
		System.out.println("ISP Name - " + this.ispName);
	}
}
