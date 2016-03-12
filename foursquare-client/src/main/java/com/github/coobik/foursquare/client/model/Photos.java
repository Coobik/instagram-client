package com.github.coobik.foursquare.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Photos {

	private Integer count;

	// TODO groups
	private List<Object> groups;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Object> getGroups() {
		return groups;
	}

	public void setGroups(List<Object> groups) {
		this.groups = groups;
	}

}
