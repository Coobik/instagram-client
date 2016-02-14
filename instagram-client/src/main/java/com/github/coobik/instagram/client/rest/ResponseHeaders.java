package com.github.coobik.instagram.client.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import com.google.common.base.Preconditions;

public class ResponseHeaders {

	private final HttpHeaders headers;

	public ResponseHeaders(HttpHeaders headers) {
		Preconditions.checkNotNull(headers, "headers");

		this.headers = headers;
	}

	public int getRateLimitRemaining() {
		return getHeaderValueAsInteger("X-Ratelimit-Remaining");
	}

	public int getRateLimit() {
		return getHeaderValueAsInteger("X-Ratelimit-Limit");
	}

	private int getHeaderValueAsInteger(String headerName) {
		String limitString = headers.getFirst(headerName);

		if (StringUtils.isBlank(limitString)) {
			return 0;
		}

		return Integer.valueOf(limitString);
	}

}
