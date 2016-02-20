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
import com.github.coobik.instagram.client.model.Tag;
import com.github.coobik.instagram.client.query.SearchParameters;

@Test(enabled = true)
@ContextConfiguration(classes = InstagramClientTestConfig.class)
public class InstagramTagServiceTest extends AbstractTestNGSpringContextTests {

	private static final String TAG_NAME = "odessa";

	@Autowired
	private InstagramClientTestConfig instagramClientTestConfig;

	@Autowired
	private InstagramClientTestHelper testHelper;

	@Autowired
	private InstagramTagService tagService;

	@Test(enabled = true)
	public void testGetTag() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		Envelope<Tag> tagEnvelope = tagService.getTag(accessToken, TAG_NAME);
		Assert.assertNotNull(tagEnvelope);
		Assert.assertNotNull(tagEnvelope.getData());

		testHelper.printJson(tagEnvelope);
	}

	@Test(enabled = true)
	public void testListTaggedMedia() throws JsonProcessingException {
		String accessToken = instagramClientTestConfig.getAccessToken();

		// TODO check with TagParameters
		Envelope<List<Media>> mediaListEnvelope = tagService.listTaggedMedia(accessToken, TAG_NAME, null);
		Assert.assertNotNull(mediaListEnvelope);
		Assert.assertNotNull(mediaListEnvelope.getData());

		testHelper.printJson(mediaListEnvelope);
	}

	@Test(enabled = true)
	public void testSearchTags() throws JsonProcessingException {
		long count = 10L;
		SearchParameters parameters = new SearchParameters(count, TAG_NAME);

		Envelope<List<Tag>> tagsListEnvelope =
				tagService.searchTags(instagramClientTestConfig.getAccessToken(), parameters);

		Assert.assertNotNull(tagsListEnvelope);
		Assert.assertNotNull(tagsListEnvelope.getData());

		testHelper.printJson(tagsListEnvelope);
	}

}
