package com.github.coobik.instagram.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribution {

	private String name;
	private String website;

	@JsonProperty("itunes_url")
	private String itunesUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getItunesUrl() {
		return itunesUrl;
	}

	public void setItunesUrl(String itunesUrl) {
		this.itunesUrl = itunesUrl;
	}

}
