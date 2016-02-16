package com.github.coobik.instagram.client.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.model.Tag;
import com.github.coobik.instagram.client.rest.InstagramRestClient;
import com.google.common.base.Preconditions;

@Service
public class InstagramTagService {

	private static final String PATH_TAGS_NAME = "tags/{tag_name}";
	private static final String PATH_TAGS_NAME_MEDIA_RECENT = PATH_TAGS_NAME + "/media/recent";

	private static final ParameterizedTypeReference<Envelope<Tag>> TYPE_TAG =
			new ParameterizedTypeReference<Envelope<Tag>>() {
			};

	@Autowired
	private InstagramRestClient restClient;

	public Envelope<Tag> getTag(String accessToken, String tagName) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(tagName), "tagName");

		Envelope<Tag> tagEnvelope =
				restClient.getObject(accessToken, PATH_TAGS_NAME, null, TYPE_TAG, tagName);

		return tagEnvelope;
	}

	public Envelope<List<Media>> listTaggedMedia(String accessToken, String tagName) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(tagName), "tagName");

		Envelope<List<Media>> mediaListEnvelope =
				restClient.getObject(
						accessToken, PATH_TAGS_NAME_MEDIA_RECENT, null, TypeReference.TYPE_MEDIA_LIST, tagName);

		return mediaListEnvelope;
	}

}
