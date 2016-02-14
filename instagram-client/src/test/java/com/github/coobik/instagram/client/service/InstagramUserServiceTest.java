package com.github.coobik.instagram.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.coobik.instagram.client.config.InstagramClientTestConfig;
import com.github.coobik.instagram.client.model.Envelope;
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
		Assert.assertNotNull(instagramUserService);

		Envelope<User> ownerEnvelope = instagramUserService.getOwner(instagramClientTestConfig.getAccessToken());
		Assert.assertNotNull(ownerEnvelope);
		Assert.assertNotNull(ownerEnvelope.getData());

		String json = objectMapper.writeValueAsString(ownerEnvelope);
		System.out.println(json);
	}

}
