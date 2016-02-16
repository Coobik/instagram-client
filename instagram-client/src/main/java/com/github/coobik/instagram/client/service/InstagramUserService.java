package com.github.coobik.instagram.client.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.model.User;
import com.github.coobik.instagram.client.rest.InstagramRestClient;
import com.google.common.base.Preconditions;

@Service
public class InstagramUserService {

	private static final String PATH_USERS_ID = "users/{user_id}";
	private static final String PATH_USERS_ID_MEDIA_RECENT = PATH_USERS_ID + "/media/recent";
	private static final String PATH_USERS_ID_MEDIA_LIKED = PATH_USERS_ID + "/media/liked";
	private static final String PATH_USERS_ID_FOLLOWS = PATH_USERS_ID + "/follows";
	private static final String PATH_USERS_ID_FOLLOWED_BY = PATH_USERS_ID + "/followed-by";

	private static final String USER_SELF = "self";

	private static final ParameterizedTypeReference<Envelope<User>> TYPE_USER =
			new ParameterizedTypeReference<Envelope<User>>() {
			};

	@Autowired
	private InstagramRestClient restClient;

	public Envelope<User> getOwner(String accessToken) {
		return getUser(accessToken, USER_SELF);
	}

	public Envelope<User> getUser(String accessToken, String userId) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(userId), "userId");

		Envelope<User> ownerEnvelope =
				restClient.getObject(accessToken, PATH_USERS_ID, null, TYPE_USER, userId);

		return ownerEnvelope;
	}

	public Envelope<List<Media>> listOwnerMedia(String accessToken) {
		return listMedia(accessToken, USER_SELF);
	}

	public Envelope<List<Media>> listMedia(String accessToken, String userId) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(userId), "userId");

		Envelope<List<Media>> mediaListEnvelope =
				restClient.getObject(
						accessToken, PATH_USERS_ID_MEDIA_RECENT, null, TypeReference.TYPE_MEDIA_LIST, userId);

		return mediaListEnvelope;
	}

	public Envelope<List<Media>> listOwnerLikedMedia(String accessToken) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");

		Envelope<List<Media>> mediaListEnvelope =
				restClient.getObject(
						accessToken, PATH_USERS_ID_MEDIA_LIKED, null, TypeReference.TYPE_MEDIA_LIST, USER_SELF);

		return mediaListEnvelope;
	}

	public Envelope<List<User>> listFollowedUsers(String accessToken) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");

		Envelope<List<User>> usersListEnvelope =
				restClient.getObject(
						accessToken, PATH_USERS_ID_FOLLOWS, null, TypeReference.TYPE_USERS_LIST, USER_SELF);

		return usersListEnvelope;
	}

	public Envelope<List<User>> listFollowers(String accessToken) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");

		Envelope<List<User>> usersListEnvelope =
				restClient.getObject(
						accessToken, PATH_USERS_ID_FOLLOWED_BY, null, TypeReference.TYPE_USERS_LIST, USER_SELF);

		return usersListEnvelope;
	}

}
