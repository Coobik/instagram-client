package com.github.coobik.instagram.client.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MediaSize {

	NONE(""),

	S150("s150x150"),
	S320("s320x320"),
	S480("s480x480"),
	S640("s640x640"),
	S750("s750x750"),
	S1080("s1080x1080"),
	S2048("s2048x2048");

	private final String size;

	private MediaSize(String size) {
		this.size = size;
	}

	@JsonValue
	public String getSize() {
		return size;
	}

}
