package com.github.coobik.instagram.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaResources {

	@JsonProperty("low_resolution")
	private MediaResource lowResolution;

	@JsonProperty("low_bandwidth")
	private MediaResource lowBandwidth;

	private MediaResource thumbnail;

	@JsonProperty("standard_resolution")
	private MediaResource standardResolution;

	public MediaResource getLowResolution() {
		return lowResolution;
	}

	public void setLowResolution(MediaResource lowResolution) {
		this.lowResolution = lowResolution;
	}

	public MediaResource getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MediaResource thumbnail) {
		this.thumbnail = thumbnail;
	}

	public MediaResource getStandardResolution() {
		return standardResolution;
	}

	public void setStandardResolution(MediaResource standardResolution) {
		this.standardResolution = standardResolution;
	}

	public MediaResource getLowBandwidth() {
		return lowBandwidth;
	}

	public void setLowBandwidth(MediaResource lowBandwidth) {
		this.lowBandwidth = lowBandwidth;
	}

}
