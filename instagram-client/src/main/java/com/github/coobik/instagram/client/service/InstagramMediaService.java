package com.github.coobik.instagram.client.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.github.coobik.instagram.client.model.Comment;
import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.model.User;
import com.github.coobik.instagram.client.query.GeoParameters;
import com.github.coobik.instagram.client.rest.InstagramRestClient;
import com.google.common.base.Preconditions;

@Service
public class InstagramMediaService {

	private static final String PATH_MEDIA_ID = "media/{media_id}";
	private static final String PATH_MEDIA_COMMENTS = PATH_MEDIA_ID + "/comments";
	private static final String PATH_MEDIA_LIKES = PATH_MEDIA_ID + "/likes";
	private static final String PATH_MEDIA_SEARCH = "media/search";
	private static final String PATH_MEDIA_SHORTCODE = "media/shortcode/{shortcode}";

	private static final ParameterizedTypeReference<Envelope<Media>> TYPE_MEDIA =
			new ParameterizedTypeReference<Envelope<Media>>() {
			};

	private static final ParameterizedTypeReference<Envelope<List<Comment>>> TYPE_COMMENTS_LIST =
			new ParameterizedTypeReference<Envelope<List<Comment>>>() {
			};

	@Autowired
	private InstagramRestClient restClient;

	public Envelope<Media> getMedia(String accessToken, String mediaId) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(mediaId), "mediaId");

		Envelope<Media> mediaEnvelope =
				restClient.getObject(accessToken, PATH_MEDIA_ID, null, TYPE_MEDIA, mediaId);

		return mediaEnvelope;
	}

	public Envelope<Media> getMediaByShortCode(String accessToken, String shortCode) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(shortCode), "shortCode");

		Envelope<Media> mediaEnvelope =
				restClient.getObject(accessToken, PATH_MEDIA_SHORTCODE, null, TYPE_MEDIA, shortCode);

		return mediaEnvelope;
	}

	public Envelope<List<Comment>> listMediaComments(String accessToken, String mediaId) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(mediaId), "mediaId");

		Envelope<List<Comment>> commentsListEnvelope =
				restClient.getObject(accessToken, PATH_MEDIA_COMMENTS, null, TYPE_COMMENTS_LIST, mediaId);

		return commentsListEnvelope;
	}

	public Envelope<List<User>> listMediaLikes(String accessToken, String mediaId) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(mediaId), "mediaId");

		Envelope<List<User>> usersListEnvelope =
				restClient.getObject(accessToken, PATH_MEDIA_LIKES, null, TypeReference.TYPE_USERS_LIST, mediaId);

		return usersListEnvelope;
	}

	public Envelope<List<Media>> searchMedia(String accessToken, GeoParameters parameters) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkNotNull(parameters, "parameters");

		Envelope<List<Media>> mediaListEnvelope =
				restClient.getObject(
						accessToken, PATH_MEDIA_SEARCH, parameters, TypeReference.TYPE_MEDIA_LIST);

		return mediaListEnvelope;
	}

}
