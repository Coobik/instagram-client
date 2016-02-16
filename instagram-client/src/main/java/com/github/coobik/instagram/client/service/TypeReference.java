package com.github.coobik.instagram.client.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.model.User;

public final class TypeReference {

	public static final ParameterizedTypeReference<Envelope<List<User>>> TYPE_USERS_LIST =
			new ParameterizedTypeReference<Envelope<List<User>>>() {
			};

	public static final ParameterizedTypeReference<Envelope<List<Media>>> TYPE_MEDIA_LIST =
			new ParameterizedTypeReference<Envelope<List<Media>>>() {
			};

	private TypeReference() {
		throw new IllegalStateException("TypeReference is a static class");
	}

}
