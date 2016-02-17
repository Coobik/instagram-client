package com.github.coobik.instagram.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Relationship {

	@JsonProperty("outgoing_status")
	private OutgoingStatus outgoingStatus;

	@JsonProperty("incoming_status")
	private IncomingStatus incomingStatus;

	@JsonProperty("target_user_is_private")
	private Boolean targetUserIsPrivate;

	public OutgoingStatus getOutgoingStatus() {
		return outgoingStatus;
	}

	public void setOutgoingStatus(OutgoingStatus outgoingStatus) {
		this.outgoingStatus = outgoingStatus;
	}

	public IncomingStatus getIncomingStatus() {
		return incomingStatus;
	}

	public void setIncomingStatus(IncomingStatus incomingStatus) {
		this.incomingStatus = incomingStatus;
	}

	public Boolean getTargetUserIsPrivate() {
		return targetUserIsPrivate;
	}

	public void setTargetUserIsPrivate(Boolean targetUserIsPrivate) {
		this.targetUserIsPrivate = targetUserIsPrivate;
	}

}
