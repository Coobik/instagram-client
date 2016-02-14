package com.github.coobik.instagram.client.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MediaType {

	IMAGE,
	VIDEO;

	@JsonCreator
	public static MediaType fromValue(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}

		return valueOf(value.toUpperCase());
	}

	@JsonValue
	public String toValue() {
		return name().toLowerCase();
	}

}
