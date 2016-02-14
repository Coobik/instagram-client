package com.github.coobik.instagram.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {

	@JsonProperty("media_count")
	private Long mediaCount;

	private String name;

	public Long getMediaCount() {
		return mediaCount;
	}

	public void setMediaCount(Long mediaCount) {
		this.mediaCount = mediaCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
