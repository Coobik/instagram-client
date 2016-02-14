package com.github.coobik.instagram.client.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.User;
import com.github.coobik.instagram.client.rest.InstagramRestClient;
import com.google.common.base.Preconditions;

@Service
public class InstagramUserService {

	private static final String PATH_USERS_SELF = "users/self";

	private static final ParameterizedTypeReference<Envelope<User>> TYPE_USER =
			new ParameterizedTypeReference<Envelope<User>>() {
			};

	@Autowired
	private InstagramRestClient restClient;

	public Envelope<User> getOwner(String accessToken) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");

		Envelope<User> ownerEnvelope = restClient.getObject(accessToken, PATH_USERS_SELF, null, TYPE_USER);
		return ownerEnvelope;
	}

}
