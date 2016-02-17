package com.github.coobik.instagram.client.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Your relationship to a user
 */
public enum OutgoingStatus {

	FOLLOWS,
	REQUESTED,
	NONE;

	@JsonCreator
	public static OutgoingStatus fromValue(String value) {
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
