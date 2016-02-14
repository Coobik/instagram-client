package com.github.coobik.instagram.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {

	@JsonProperty("next_url")
	private String nextUrl;

	@JsonProperty("next_max_id")
	private String nextMaxId;

}
