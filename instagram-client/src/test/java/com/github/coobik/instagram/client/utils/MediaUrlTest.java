package com.github.coobik.instagram.client.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(enabled = true)
public class MediaUrlTest {

	private static final String LINK = "https://www.instagram.com/p/BCbUMqBBQUJ";
	private static final String SHORT_CODE = "BCbUMqBBQUJ";

	private static final String URL_STANDARD =
			"https://scontent-ams3-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/12749994_803233789808942_902072097_n.jpg?ig_cache_key=MTE5MjMzMjA1MTgzNjM3MjI2NQ%3D%3D.2";

	private static final String URL_ORIGINAL =
			"https://scontent-ams3-1.cdninstagram.com/t51.2885-15/12749994_803233789808942_902072097_n.jpg";

	private static final String URL_ORIGINAL_WITH_QUERY =
			"https://scontent-ams3-1.cdninstagram.com/t51.2885-15/12749994_803233789808942_902072097_n.jpg?ig_cache_key=MTE5MjMzMjA1MTgzNjM3MjI2NQ%3D%3D.2";

	private static final String URL_SIZE_1080_SHARP009_E45 =
			"https://scontent-ams3-1.cdninstagram.com/t51.2885-15/s1080x1080/sh0.09/e45/12749994_803233789808942_902072097_n.jpg";

	private static final String URL_BASE = "https://scontent-ams3-1.cdninstagram.com/t51.2885-15/";
	private static final String MEDIA_NAME = "12749994_803233789808942_902072097_n.jpg";
	private static final String QUERY = "?ig_cache_key=MTE5MjMzMjA1MTgzNjM3MjI2NQ%3D%3D.2";

	@Test(enabled = true)
	public void testMediaUrl() {
		MediaUrl mediaUrl = MediaUrl.build(URL_STANDARD);
		Assert.assertNotNull(mediaUrl);

		Assert.assertEquals(mediaUrl.getUrlBase(), URL_BASE);
		Assert.assertEquals(mediaUrl.getMediaName(), MEDIA_NAME);
		Assert.assertEquals(mediaUrl.getQuery(), QUERY);

		Assert.assertEquals(mediaUrl.getUrlWithDefaults(MediaSize.S640, true), URL_STANDARD);
		Assert.assertEquals(mediaUrl.getUrl(), URL_ORIGINAL);
		Assert.assertEquals(mediaUrl.getUrl(null, null, null, true), URL_ORIGINAL_WITH_QUERY);
		Assert.assertEquals(mediaUrl.getUrl(MediaSize.S1080, "0.09", "45", false), URL_SIZE_1080_SHARP009_E45);

		Assert.assertEquals(mediaUrl.toString(), URL_ORIGINAL);
	}

	@Test(enabled = true)
	public void testShortCode() {
		String shortCode = MediaUrl.getShortCode(LINK);
		Assert.assertEquals(shortCode, SHORT_CODE);

		shortCode = MediaUrl.getShortCode(LINK + "/");
		Assert.assertEquals(shortCode, SHORT_CODE);

		shortCode = MediaUrl.getShortCode("");
		Assert.assertEquals(shortCode, "");
	}

}
