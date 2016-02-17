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
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.model.Relationship;
import com.github.coobik.instagram.client.model.User;

@Test(enabled = true)
@ContextConfiguration(classes = InstagramClientTestConfig.class)
public class InstagramUserServiceTest extends AbstractTestNGSpringContextTests {

	private static final String INSTAGRAM_USER_ID = "25025320";

	@Autowired
	private InstagramUserService instagramUserService;

	@Autowired
	private InstagramClientTestConfig instagramClientTestConfig;

	@Autowired
	private InstagramClientTestHelper testHelper;

	@Test(enabled = true)
	public void testGetOwner() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		Envelope<User> ownerEnvelope = instagramUserService.getOwner(accessToken);
		Assert.assertNotNull(ownerEnvelope);

		User owner = ownerEnvelope.getData();
		Assert.assertNotNull(owner);

		testHelper.printJson(ownerEnvelope);

		Envelope<User> userEnvelope = instagramUserService.getUser(accessToken, owner.getId());
		Assert.assertNotNull(userEnvelope);

		Envelope<List<Media>> listMediaEnvelope = instagramUserService.listMedia(accessToken, owner.getId());
		Assert.assertNotNull(listMediaEnvelope);

		testHelper.printJson(listMediaEnvelope);
	}

	@Test(enabled = true)
	public void testListOwnerMedia() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		Envelope<List<Media>> listMediaEnvelope = instagramUserService.listOwnerMedia(accessToken);
		Assert.assertNotNull(listMediaEnvelope);

		testHelper.printJson(listMediaEnvelope);

		listMediaEnvelope = instagramUserService.listOwnerLikedMedia(accessToken);
		Assert.assertNotNull(listMediaEnvelope);

		testHelper.printJson(listMediaEnvelope);
	}

	@Test(enabled = true)
	public void testListFollowers() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		checkFollowedUsers(accessToken);
		checkFollowers(accessToken);
	}

	private void checkFollowedUsers(String accessToken) throws JsonProcessingException {
		Envelope<List<User>> usersListEnvelope = instagramUserService.listFollowedUsers(accessToken);
		Assert.assertNotNull(usersListEnvelope);
		Assert.assertNotNull(usersListEnvelope.getData());

		testHelper.printJson(usersListEnvelope);
	}

	private void checkFollowers(String accessToken) throws JsonProcessingException {
		Envelope<List<User>> usersListEnvelope = instagramUserService.listFollowers(accessToken);
		Assert.assertNotNull(usersListEnvelope);
		Assert.assertNotNull(usersListEnvelope.getData());

		testHelper.printJson(usersListEnvelope);
	}

	@Test(enabled = true)
	public void testRelationship() throws JsonProcessingException {
		Envelope<Relationship> relationshipEnvelope =
				instagramUserService.getRelationship(instagramClientTestConfig.getAccessToken(), INSTAGRAM_USER_ID);
		Assert.assertNotNull(relationshipEnvelope);
		Assert.assertNotNull(relationshipEnvelope.getData());

		testHelper.printJson(relationshipEnvelope);
	}

}
