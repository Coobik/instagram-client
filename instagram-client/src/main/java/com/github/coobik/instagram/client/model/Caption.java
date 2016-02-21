package com.github.coobik.instagram.client.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.coobik.instagram.client.rest.InstagramTimeDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Caption {

	/**
	 * Time in millis
	 */
	@JsonProperty("created_time")
	@JsonDeserialize(using = InstagramTimeDeserializer.class)
	private DateTime createdTime;

	private String text;
	private User from;

	private String id;

	public DateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(DateTime createdTime) {
		this.createdTime = createdTime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
