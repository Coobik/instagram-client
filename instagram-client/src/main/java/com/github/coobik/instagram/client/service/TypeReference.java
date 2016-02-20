package com.github.coobik.instagram.client.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Location;
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.model.Relationship;
import com.github.coobik.instagram.client.model.Tag;
import com.github.coobik.instagram.client.model.User;

public final class TypeReference {

	public static final ParameterizedTypeReference<Envelope<List<User>>> TYPE_USERS_LIST =
			new ParameterizedTypeReference<Envelope<List<User>>>() {
			};

	public static final ParameterizedTypeReference<Envelope<List<Media>>> TYPE_MEDIA_LIST =
			new ParameterizedTypeReference<Envelope<List<Media>>>() {
			};

	public static final ParameterizedTypeReference<Envelope<Location>> TYPE_LOCATION =
			new ParameterizedTypeReference<Envelope<Location>>() {
			};

	public static final ParameterizedTypeReference<Envelope<Relationship>> TYPE_RELATIONSHIP =
			new ParameterizedTypeReference<Envelope<Relationship>>() {
			};

	public static final ParameterizedTypeReference<Envelope<List<Tag>>> TYPE_TAGS_LIST =
			new ParameterizedTypeReference<Envelope<List<Tag>>>() {
			};

	public static final ParameterizedTypeReference<Envelope<List<Location>>> TYPE_LOCATIONS_LIST =
			new ParameterizedTypeReference<Envelope<List<Location>>>() {
			};

	private TypeReference() {
		throw new IllegalStateException("TypeReference is a static class");
	}

}
