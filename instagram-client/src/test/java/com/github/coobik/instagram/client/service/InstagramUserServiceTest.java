package com.github.coobik.instagram.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.coobik.instagram.client.config.InstagramClientTestConfig;
import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.model.User;

@Test(enabled = true)
@ContextConfiguration(classes = InstagramClientTestConfig.class)
public class InstagramUserServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private InstagramUserService instagramUserService;

	@Autowired
	private InstagramClientTestConfig instagramClientTestConfig;

	@Autowired
	private ObjectMapper objectMapper;

	@Test(enabled = true)
	public void testGetOwner() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		Envelope<User> ownerEnvelope = instagramUserService.getOwner(accessToken);
		Assert.assertNotNull(ownerEnvelope);

		User owner = ownerEnvelope.getData();
		Assert.assertNotNull(owner);

		printJson(ownerEnvelope);

		Envelope<User> userEnvelope = instagramUserService.getUser(accessToken, owner.getId());
		Assert.assertNotNull(userEnvelope);

		Envelope<List<Media>> listMediaEnvelope = instagramUserService.listMedia(accessToken, owner.getId());
		Assert.assertNotNull(listMediaEnvelope);

		printJson(listMediaEnvelope);
	}

	@Test(enabled = true)
	public void testListOwnerMedia() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		Envelope<List<Media>> listMediaEnvelope = instagramUserService.listOwnerMedia(accessToken);
		Assert.assertNotNull(listMediaEnvelope);

		printJson(listMediaEnvelope);

		listMediaEnvelope = instagramUserService.listOwnerLikedMedia(accessToken);
		Assert.assertNotNull(listMediaEnvelope);

		printJson(listMediaEnvelope);
	}

	private void printJson(Object entity) throws JsonProcessingException {
		String json = objectMapper.writeValueAsString(entity);
		System.out.println(json);
	}

}
