package com.github.coobik.foursquare.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {

	private Integer checkinsCount;
	private Integer usersCount;
	private Integer tipCount;
	private Integer visitsCount;

	public Integer getCheckinsCount() {
		return checkinsCount;
	}

	public void setCheckinsCount(Integer checkinsCount) {
		this.checkinsCount = checkinsCount;
	}

	public Integer getUsersCount() {
		return usersCount;
	}

	public void setUsersCount(Integer usersCount) {
		this.usersCount = usersCount;
	}

	public Integer getTipCount() {
		return tipCount;
	}

	public void setTipCount(Integer tipCount) {
		this.tipCount = tipCount;
	}

	public Integer getVisitsCount() {
		return visitsCount;
	}

	public void setVisitsCount(Integer visitsCount) {
		this.visitsCount = visitsCount;
	}

}
