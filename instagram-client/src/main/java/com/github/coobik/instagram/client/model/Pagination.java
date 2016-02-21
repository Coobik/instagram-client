package com.github.coobik.instagram.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {

	@JsonProperty("next_url")
	private String nextUrl;

	@JsonProperty("next_max_id")
	private String nextMaxId;

	@JsonProperty("next_cursor")
	private String nextCursor;

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public String getNextMaxId() {
		return nextMaxId;
	}

	public void setNextMaxId(String nextMaxId) {
		this.nextMaxId = nextMaxId;
	}

	public String getNextCursor() {
		return nextCursor;
	}

	public void setNextCursor(String nextCursor) {
		this.nextCursor = nextCursor;
	}

}
