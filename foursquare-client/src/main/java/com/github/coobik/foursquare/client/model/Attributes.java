package com.github.coobik.foursquare.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attributes {

	// TODO groups
	private List<Object> groups;

	public List<Object> getGroups() {
		return groups;
	}

	public void setGroups(List<Object> groups) {
		this.groups = groups;
	}

}
