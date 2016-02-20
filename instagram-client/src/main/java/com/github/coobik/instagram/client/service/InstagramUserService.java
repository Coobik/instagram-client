package com.github.coobik.instagram.client.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.model.Relationship;
import com.github.coobik.instagram.client.model.User;
import com.github.coobik.instagram.client.query.IdParameters;
import com.github.coobik.instagram.client.query.LikeParameters;
import com.github.coobik.instagram.client.query.SearchParameters;
import com.github.coobik.instagram.client.rest.InstagramRestClient;
import com.google.common.base.Preconditions;

@Service
public class InstagramUserService {

	private static final String PATH_USERS_ID = "users/{user_id}";
	private static final String PATH_USERS_ID_MEDIA_RECENT = PATH_USERS_ID + "/media/recent";
	private static final String PATH_USERS_ID_MEDIA_LIKED = PATH_USERS_ID + "/media/liked";
	private static final String PATH_USERS_ID_FOLLOWS = PATH_USERS_ID + "/follows";
	private static final String PATH_USERS_ID_FOLLOWED_BY = PATH_USERS_ID + "/followed-by";
	private static final String PATH_USERS_ID_RELATIONSHIP = PATH_USERS_ID + "/relationship";
	private static final String PATH_USERS_SEARCH = "users/search";

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

	public Envelope<List<Media>> listOwnerMedia(String accessToken, IdParameters parameters) {
		return listMedia(accessToken, USER_SELF, parameters);
	}

	public Envelope<List<Media>> listMedia(String accessToken, String userId, IdParameters parameters) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(userId), "userId");

		Envelope<List<Media>> mediaListEnvelope =
				restClient.getObject(
						accessToken, PATH_USERS_ID_MEDIA_RECENT, parameters, TypeReference.TYPE_MEDIA_LIST, userId);

		return mediaListEnvelope;
	}

	public Envelope<List<Media>> listOwnerLikedMedia(String accessToken, LikeParameters parameters) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");

		Envelope<List<Media>> mediaListEnvelope =
				restClient.getObject(
						accessToken, PATH_USERS_ID_MEDIA_LIKED, parameters, TypeReference.TYPE_MEDIA_LIST, USER_SELF);

		return mediaListEnvelope;
	}

	public Envelope<List<User>> listOwnerFollowedUsers(String accessToken) {
		return listFollowedUsers(accessToken, USER_SELF);
	}

	public Envelope<List<User>> listFollowedUsers(String accessToken, String userId) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");

		Envelope<List<User>> usersListEnvelope =
				restClient.getObject(
						accessToken, PATH_USERS_ID_FOLLOWS, null, TypeReference.TYPE_USERS_LIST, userId);

		return usersListEnvelope;
	}

	public Envelope<List<User>> listOwnerFollowers(String accessToken) {
		return listFollowers(accessToken, USER_SELF);
	}

	public Envelope<List<User>> listFollowers(String accessToken, String userId) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");

		Envelope<List<User>> usersListEnvelope =
				restClient.getObject(
						accessToken, PATH_USERS_ID_FOLLOWED_BY, null, TypeReference.TYPE_USERS_LIST, userId);

		return usersListEnvelope;
	}

	public Envelope<Relationship> getRelationship(String accessToken, String userId) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(userId), "userId");

		Envelope<Relationship> relationshipEnvelope =
				restClient.getObject(
						accessToken, PATH_USERS_ID_RELATIONSHIP, null, TypeReference.TYPE_RELATIONSHIP, userId);

		return relationshipEnvelope;
	}

	public Envelope<List<User>> searchUsers(String accessToken, SearchParameters parameters) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkNotNull(parameters, "parameters");

		Envelope<List<User>> usersListEnvelope =
				restClient.getObject(
						accessToken, PATH_USERS_SEARCH, parameters, TypeReference.TYPE_USERS_LIST);

		return usersListEnvelope;
	}

}
