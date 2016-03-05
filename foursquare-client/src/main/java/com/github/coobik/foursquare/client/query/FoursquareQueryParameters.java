package com.github.coobik.foursquare.client.query;

import com.github.coobik.api.client.query.QueryParameters;

public class FoursquareQueryParameters extends QueryParameters {

	public static final FoursquareQueryParameters DEFAULT = new FoursquareQueryParameters();

	public FoursquareQueryParameters() {
		setMode("foursquare");
		setVersion("20140806");
	}

	private void setMode(String mode) {
		setParameter("m", mode);
	}

	private void setVersion(String version) {
		setParameter("v", version);
	}

}
