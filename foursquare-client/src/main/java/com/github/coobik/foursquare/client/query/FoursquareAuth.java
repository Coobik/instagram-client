package com.github.coobik.foursquare.client.query;

import org.apache.commons.lang3.StringUtils;

import com.github.coobik.api.client.query.QueryParameters;
import com.google.common.base.Preconditions;

public class FoursquareAuth extends QueryParameters {

	public FoursquareAuth(String clientId, String clientSecret) {
		setClientId(clientId);
		setClientSecret(clientSecret);
	}

	public FoursquareAuth(String oauthToken) {
		setOauthToken(oauthToken);
	}

	private void setOauthToken(String oauthToken) {
		Preconditions.checkArgument(StringUtils.isNotBlank(oauthToken), "oauthToken");

		setParameter("oauth_token", oauthToken);
	}

	private void setClientId(String clientId) {
		Preconditions.checkArgument(StringUtils.isNotBlank(clientId), "clientId");

		setParameter("client_id", clientId);
	}

	private void setClientSecret(String clientSecret) {
		Preconditions.checkArgument(StringUtils.isNotBlank(clientSecret), "clientSecret");

		setParameter("client_secret", clientSecret);
	}

}
