package com.github.coobik.instagram.client.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.coobik.instagram.client.config.InstagramClientTestConfig;
import com.github.coobik.instagram.client.config.ClientTestHelper;
import com.github.coobik.instagram.client.model.Comment;
import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.model.User;
import com.github.coobik.instagram.client.query.GeoParameters;
import com.github.coobik.instagram.client.utils.MediaUrl;

@Test(enabled = true)
@ContextConfiguration(classes = InstagramClientTestConfig.class)
public class InstagramMediaServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private InstagramClientTestConfig instagramClientTestConfig;

	@Autowired
	private ClientTestHelper testHelper;

	@Autowired
	private InstagramMediaService mediaService;

	@Autowired
	private InstagramUserService instagramUserService;

	@Test(enabled = true)
	public void testGetMedia() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		List<Media> mediaList = listOwnerMedia(accessToken);

		if (CollectionUtils.isEmpty(mediaList)) {
			return;
		}

		Media mediaFromList = mediaList.get(0);
		Assert.assertNotNull(mediaFromList);

		String mediaId = mediaFromList.getId();

		Media media = checkMedia(accessToken, mediaId);
		checkMediaByShortCode(accessToken, media);

		checkMediaComments(accessToken, mediaId);
		checkMediaLikes(accessToken, mediaId);
	}

	private void checkMediaByShortCode(String accessToken, Media media) {
		String link = media.getLink();
		String shortCode = MediaUrl.getShortCode(link);
		Envelope<Media> mediaByShortCode = mediaService.getMediaByShortCode(accessToken, shortCode);
		Assert.assertEquals(media.getId(), mediaByShortCode.getData().getId());

		System.out.print("link: " + link);
		System.out.println(" short code: " + shortCode);
	}

	private void checkMediaLikes(String accessToken, String mediaId) throws JsonProcessingException {
		Envelope<List<User>> likedUsersEnvelope = mediaService.listMediaLikes(accessToken, mediaId);

		Assert.assertNotNull(likedUsersEnvelope);
		Assert.assertNotNull(likedUsersEnvelope.getData());

		testHelper.printJson(likedUsersEnvelope);
	}

	private void checkMediaComments(String accessToken, String mediaId) throws JsonProcessingException {
		Envelope<List<Comment>> commentsListEnvelope = mediaService.listMediaComments(accessToken, mediaId);

		Assert.assertNotNull(commentsListEnvelope);
		Assert.assertNotNull(commentsListEnvelope.getData());

		testHelper.printJson(commentsListEnvelope);
	}

	private Media checkMedia(String accessToken, String mediaId) throws JsonProcessingException {
		Envelope<Media> mediaEnvelope = mediaService.getMedia(accessToken, mediaId);
		Assert.assertNotNull(mediaEnvelope);

		Media media = mediaEnvelope.getData();
		Assert.assertNotNull(media);

		testHelper.printJson(mediaEnvelope);

		return media;
	}

	private List<Media> listOwnerMedia(String accessToken) {
		Envelope<List<Media>> listMediaEnvelope = instagramUserService.listOwnerMedia(accessToken, null);
		List<Media> mediaList = listMediaEnvelope.getData();
		return mediaList;
	}

	@Test(enabled = true)
	public void testSearchMedia() throws JsonProcessingException {
		double latitude = 46.4667;
		double longitude = 30.7333;
		int distanceMeters = 5000;

		GeoParameters parameters = new GeoParameters(latitude, longitude, distanceMeters);
		// parameters.setCount(2);

		Envelope<List<Media>> mediaListEnvelope =
				mediaService.searchMedia(instagramClientTestConfig.getAccessToken(), parameters);

		Assert.assertNotNull(mediaListEnvelope);
		Assert.assertNotNull(mediaListEnvelope.getData());

		testHelper.printJson(mediaListEnvelope);
	}

}
