package com.github.coobik.foursquare.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhraseSample {

	private List<FoursquareEntity> entities;
	private String text;

	public List<FoursquareEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<FoursquareEntity> entities) {
		this.entities = entities;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
