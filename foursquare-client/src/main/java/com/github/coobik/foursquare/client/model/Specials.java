package com.github.coobik.foursquare.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Specials {

	private Integer count;

	private List<Special> items;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Special> getItems() {
		return items;
	}

	public void setItems(List<Special> items) {
		this.items = items;
	}

}
