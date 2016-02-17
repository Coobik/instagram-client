package com.github.coobik.instagram.client.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * User relationship to you
 */
public enum IncomingStatus {

	FOLLOWED_BY,
	REQUESTED_BY,
	BLOCKED_BY_YOU,
	NONE;

	@JsonCreator
	public static IncomingStatus fromValue(String value) {
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
