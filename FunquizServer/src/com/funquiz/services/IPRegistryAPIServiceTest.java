package com.funquiz.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.funquiz.models.Location;

/**
 * Test class to execute and validate operations related to IPRegistryAPIService
 * 
 * @author Oshadee Amarasinghe
 *
 */
class IPRegistryAPIServiceTest {

	/**
	 * To test retrieval of location details based on a valid IP
	 */
	@Test
	void validIPRegistryGetLocationTest() {
		IPRegistryAPIService apiService = new IPRegistryAPIService();
		Location userLocation = apiService.getLocation("112.134.132.164");
		assertNotNull(userLocation.getIp());
		assertNotNull(userLocation.getCity());
		assertNotNull(userLocation.getPostalCode());
		assertNotNull(userLocation.getLatitude());
		assertNotNull(userLocation.getLongitude());
		assertNotNull(userLocation.getLongitude());
		assertNotNull(userLocation.getIspName());
	}
	
	/**
	 * To test retrieval of location details based on a invalid IP
	 */
	@Test
	void invalidIPRegistryGetLocationTest() {
		IPRegistryAPIService apiService = new IPRegistryAPIService();
		assertNull(apiService.getLocation("134.132.164"));
	}
}
