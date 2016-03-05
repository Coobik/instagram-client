package com.github.coobik.instagram.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.coobik.instagram.client.config.InstagramClientTestConfig;
import com.github.coobik.instagram.client.config.ClientTestHelper;
import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Location;
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.query.LocationParameters;

@Test(enabled = true)
@ContextConfiguration(classes = InstagramClientTestConfig.class)
public class InstagramLocationServiceTest extends AbstractTestNGSpringContextTests {

	/**
	 * "latitude" : 46.4667, "longitude" : 30.7333, "name" : "Odessa, Ukraine"
	 */
	private static final String LOCATION_ID = "217478883";

	@Autowired
	private InstagramClientTestConfig instagramClientTestConfig;

	@Autowired
	private ClientTestHelper testHelper;

	@Autowired
	private InstagramLocationService instagramLocationService;

	@Test(enabled = true)
	public void testGetLocation() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		Envelope<Location> locationEnvelope = instagramLocationService.getLocation(accessToken, LOCATION_ID);
		Assert.assertNotNull(locationEnvelope);
		Assert.assertNotNull(locationEnvelope.getData());

		testHelper.printJson(locationEnvelope);
	}

	@Test(enabled = true)
	public void testListLocationMedia() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		// TODO check with IdParameters
		Envelope<List<Media>> mediaListEnvelope = instagramLocationService.listMedia(accessToken, LOCATION_ID, null);
		Assert.assertNotNull(mediaListEnvelope);
		Assert.assertNotNull(mediaListEnvelope.getData());

		testHelper.printJson(mediaListEnvelope);
	}

	@Test(enabled = true)
	public void testSearchLocations() throws JsonProcessingException {
		double latitude = 46.4667;
		double longitude = 30.7333;
		int distanceMeters = 5000;

		LocationParameters parameters = new LocationParameters(latitude, longitude, distanceMeters);
		parameters.setCount(10);

		Envelope<List<Location>> locationsListEnvelope =
				instagramLocationService.searchLocations(instagramClientTestConfig.getAccessToken(), parameters);

		Assert.assertNotNull(locationsListEnvelope);

		List<Location> locations = locationsListEnvelope.getData();
		Assert.assertNotNull(locations);

		System.out.println("Locations: " + locations.size());

		testHelper.printJson(locationsListEnvelope);
	}

}
