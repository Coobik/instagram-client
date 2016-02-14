package com.github.coobik.instagram.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCounts {

	private Long media;
	private Long follows;

	@JsonProperty("followed_by")
	private Long followedBy;

	public Long getMedia() {
		return media;
	}

	public void setMedia(Long media) {
		this.media = media;
	}

	public Long getFollows() {
		return follows;
	}

	public void setFollows(Long follows) {
		this.follows = follows;
	}

	public Long getFollowedBy() {
		return followedBy;
	}

	public void setFollowedBy(Long followedBy) {
		this.followedBy = followedBy;
	}

}
