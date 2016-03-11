package com.github.coobik.foursquare.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Icon {

	private String prefix;
	private String suffix;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	// Combine prefix with a size (32, 44, 64, and 88 are available) and suffix,
	// https://foursquare.com/img/categories/food/default_64.png.
	// To get an image with a gray background, use bg_ before the size

}
