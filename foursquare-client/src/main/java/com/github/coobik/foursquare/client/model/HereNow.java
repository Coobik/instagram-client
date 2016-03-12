package com.github.coobik.foursquare.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HereNow {

	private Integer count;
	private String summary;

	// TODO what is in groups ???
	private List<Object> groups;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Object> getGroups() {
		return groups;
	}

	public void setGroups(List<Object> groups) {
		this.groups = groups;
	}

}
