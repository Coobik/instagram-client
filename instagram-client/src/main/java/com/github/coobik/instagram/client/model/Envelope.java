package com.github.coobik.instagram.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.coobik.instagram.client.rest.ResponseHeaders;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Envelope<T> {

	private Meta meta;
	private Pagination pagination;

	private T data;

	@JsonIgnore
	private ResponseHeaders responseHeaders;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseHeaders getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(ResponseHeaders responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

}
