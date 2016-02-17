package com.github.coobik.instagram.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.coobik.instagram.client.config.InstagramClientTestConfig;
import com.github.coobik.instagram.client.config.InstagramClientTestHelper;
import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Location;
import com.github.coobik.instagram.client.model.Media;

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
	private InstagramClientTestHelper testHelper;

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

		Envelope<List<Media>> mediaListEnvelope = instagramLocationService.listMedia(accessToken, LOCATION_ID);
		Assert.assertNotNull(mediaListEnvelope);
		Assert.assertNotNull(mediaListEnvelope.getData());

		testHelper.printJson(mediaListEnvelope);
	}

}
