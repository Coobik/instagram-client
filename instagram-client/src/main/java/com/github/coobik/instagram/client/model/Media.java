package com.github.coobik.instagram.client.model;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.coobik.instagram.client.rest.InstagramTimeDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Media {

	private String id;

	private Count<Comment> comments;
	private Count<User> likes;

	private Caption caption;

	private String link;
	private String filter;

	/**
	 * Time in seconds
	 */
	@JsonProperty("created_time")
	@JsonDeserialize(using = InstagramTimeDeserializer.class)
	private DateTime createdTime;

	private User user;

	private MediaResources images;
	private MediaResources videos;

	private MediaType type;

	@JsonProperty("users_in_photo")
	private List<UserInPhoto> usersInPhoto;

	private Location location;

	private List<String> tags;

	@JsonProperty("user_has_liked")
	private Boolean userHasLiked;

	private Attribution attribution;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Count<Comment> getComments() {
		return comments;
	}

	public void setComments(Count<Comment> comments) {
		this.comments = comments;
	}

	public Count<User> getLikes() {
		return likes;
	}

	public void setLikes(Count<User> likes) {
		this.likes = likes;
	}

	public Caption getCaption() {
		return caption;
	}

	public void setCaption(Caption caption) {
		this.caption = caption;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public DateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(DateTime createdTime) {
		this.createdTime = createdTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MediaResources getImages() {
		return images;
	}

	public void setImages(MediaResources images) {
		this.images = images;
	}

	public MediaResources getVideos() {
		return videos;
	}

	public void setVideos(MediaResources videos) {
		this.videos = videos;
	}

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}

	public List<UserInPhoto> getUsersInPhoto() {
		return usersInPhoto;
	}

	public void setUsersInPhoto(List<UserInPhoto> usersInPhoto) {
		this.usersInPhoto = usersInPhoto;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Boolean getUserHasLiked() {
		return userHasLiked;
	}

	public void setUserHasLiked(Boolean userHasLiked) {
		this.userHasLiked = userHasLiked;
	}

	public Attribution getAttribution() {
		return attribution;
	}

	public void setAttribution(Attribution attribution) {
		this.attribution = attribution;
	}

}
