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
import com.github.coobik.instagram.client.query.SearchParameters;

@Test(enabled = true)
@ContextConfiguration(classes = InstagramClientTestConfig.class)
public class InstagramUserServiceTest extends AbstractTestNGSpringContextTests {

	private static final String INSTAGRAM_USER_NAME = "instagram";
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

		Envelope<List<Media>> listMediaEnvelope = instagramUserService.listMedia(accessToken, owner.getId(), null);
		Assert.assertNotNull(listMediaEnvelope);

		testHelper.printJson(listMediaEnvelope);
	}

	@Test(enabled = true)
	public void testListOwnerMedia() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		Envelope<List<Media>> listMediaEnvelope = instagramUserService.listOwnerMedia(accessToken, null);
		Assert.assertNotNull(listMediaEnvelope);

		testHelper.printJson(listMediaEnvelope);

		listMediaEnvelope = instagramUserService.listOwnerLikedMedia(accessToken, null);
		Assert.assertNotNull(listMediaEnvelope);

		testHelper.printJson(listMediaEnvelope);
	}

	@Test(enabled = true)
	public void testListFollowers() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		checkFollowedUsers(accessToken);
		checkFollowers(accessToken);
	}

	@Test(enabled = true)
	public void testListUserFollows() throws JsonProcessingException {
		Envelope<List<User>> usersListEnvelope =
				instagramUserService.listFollowedUsers(
						instagramClientTestConfig.getAccessToken(), INSTAGRAM_USER_ID, null);
		Assert.assertNotNull(usersListEnvelope);

		List<User> followedUsers = usersListEnvelope.getData();
		Assert.assertNotNull(followedUsers);

		System.out.println("followed users: " + followedUsers.size());

		testHelper.printJson(usersListEnvelope);
	}

	@Test(enabled = true)
	public void testListUserFollowers() throws JsonProcessingException {
		Envelope<List<User>> usersListEnvelope =
				instagramUserService.listFollowers(
						instagramClientTestConfig.getAccessToken(), INSTAGRAM_USER_ID, null);
		Assert.assertNotNull(usersListEnvelope);

		List<User> followedUsers = usersListEnvelope.getData();
		Assert.assertNotNull(followedUsers);

		System.out.println("followers: " + followedUsers.size());

		testHelper.printJson(usersListEnvelope);
	}

	private void checkFollowedUsers(String accessToken) throws JsonProcessingException {
		Envelope<List<User>> usersListEnvelope = instagramUserService.listOwnerFollowedUsers(accessToken, null);
		Assert.assertNotNull(usersListEnvelope);
		Assert.assertNotNull(usersListEnvelope.getData());

		testHelper.printJson(usersListEnvelope);
	}

	private void checkFollowers(String accessToken) throws JsonProcessingException {
		Envelope<List<User>> usersListEnvelope = instagramUserService.listOwnerFollowers(accessToken, null);
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

	@Test(enabled = true)
	public void testSearchUsers() throws JsonProcessingException {
		int count = 10;
		SearchParameters parameters = new SearchParameters(count, INSTAGRAM_USER_NAME);
		Envelope<List<User>> usersListEnvelope =
				instagramUserService.searchUsers(instagramClientTestConfig.getAccessToken(), parameters);
		Assert.assertNotNull(usersListEnvelope);

		List<User> users = usersListEnvelope.getData();
		Assert.assertNotNull(users);
		System.out.println("users loaded: " + users.size());
		Assert.assertTrue(users.size() <= count);

		testHelper.printJson(usersListEnvelope);
	}

	@Test(enabled = true)
	public void testListOwnerFeed() throws JsonProcessingException {
		// TODO check with parameters
		Envelope<List<Media>> mediaListEnvelope =
				instagramUserService.listOwnerFeed(instagramClientTestConfig.getAccessToken(), null);
		Assert.assertNotNull(mediaListEnvelope);

		List<Media> media = mediaListEnvelope.getData();
		Assert.assertNotNull(media);

		System.out.println("loaded feed media: " + media.size());

		testHelper.printJson(mediaListEnvelope);
	}

}
