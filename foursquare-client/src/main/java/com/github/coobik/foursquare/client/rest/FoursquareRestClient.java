package com.github.coobik.foursquare.client.rest;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.coobik.foursquare.client.model.FoursquareResponse;
import com.github.coobik.foursquare.client.query.FoursquareAuth;
import com.github.coobik.foursquare.client.query.FoursquareQueryParameters;
import com.google.common.base.Preconditions;

@Component
public class FoursquareRestClient {

	private static final String FOURSQUARE_API_URL = "https://api.foursquare.com/v2";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RestTemplate restTemplate;

	public <RESPONSE> FoursquareResponse<RESPONSE> getObject(FoursquareAuth auth, String resource,
			ParameterizedTypeReference<FoursquareResponse<RESPONSE>> responseType, Object... uriVariables) {
		return getObject(auth, resource, FoursquareQueryParameters.DEFAULT, responseType, uriVariables);
	}

	public <RESPONSE> FoursquareResponse<RESPONSE> getObject(FoursquareAuth auth, String resource,
			FoursquareQueryParameters parameters,
			ParameterizedTypeReference<FoursquareResponse<RESPONSE>> responseType, Object... uriVariables) {
		return exchange(HttpMethod.GET, auth, resource, parameters, null, responseType, uriVariables);
	}

	private <BODY, RESPONSE> FoursquareResponse<RESPONSE> exchange(HttpMethod method, FoursquareAuth auth,
			String resource, FoursquareQueryParameters parameters, BODY body,
			ParameterizedTypeReference<FoursquareResponse<RESPONSE>> responseType, Object... uriVariables) {
		String url = buildUrl(auth, resource, parameters, uriVariables);
		HttpEntity<BODY> request = createHttpEntity(body);
		ResponseEntity<FoursquareResponse<RESPONSE>> response =
				restTemplate.exchange(url, method, request, responseType);

		FoursquareResponse<RESPONSE> envelope = extractEnvelope(response);
		return envelope;
	}

	private <RESPONSE> FoursquareResponse<RESPONSE> extractEnvelope(
			ResponseEntity<FoursquareResponse<RESPONSE>> response) {
		FoursquareResponse<RESPONSE> envelope = response.getBody();

		// ResponseHeaders responseHeaders = new ResponseHeaders(response.getHeaders());
		// envelope.setResponseHeaders(responseHeaders);

		return envelope;
	}

	private <BODY> HttpEntity<BODY> createHttpEntity(BODY body) {
		HttpHeaders headers = new HttpHeaders();

		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		return new HttpEntity<BODY>(body, headers);
	}

	private String buildUrl(FoursquareAuth auth, String resource, FoursquareQueryParameters parameters,
			Object... uriVariables) {
		Preconditions.checkNotNull(auth, "auth");
		Preconditions.checkNotNull(parameters, "parameters");

		UriComponentsBuilder builder =
				UriComponentsBuilder
						.fromHttpUrl(FOURSQUARE_API_URL)
						.pathSegment(resource);

		for (Entry<String, String> authEntry : auth.getParameters().entrySet()) {
			builder.queryParam(authEntry.getKey(), authEntry.getValue());
		}

		for (Entry<String, String> parameterEntry : parameters.getParameters().entrySet()) {
			builder.queryParam(parameterEntry.getKey(), parameterEntry.getValue());
		}

		return builder.buildAndExpand(uriVariables).toUriString();
	}

}
