package com.github.coobik.instagram.client.rest;

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
import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.query.QueryParameters;

@Component
public class InstagramRestClient {

	private static final String INSTAGRAM_API_URL = "https://api.instagram.com/v1";
	private static final String PARAMETER_ACCESS_TOKEN = "access_token";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RestTemplate restTemplate;

	public <RESPONSE> Envelope<RESPONSE> getObject(String accessToken, String resource,
			QueryParameters parameters,
			ParameterizedTypeReference<Envelope<RESPONSE>> responseType, Object... uriVariables) {
		return exchange(HttpMethod.GET, accessToken, resource, parameters, null, responseType, uriVariables);
	}

	private <BODY, RESPONSE> Envelope<RESPONSE> exchange(HttpMethod method, String accessToken,
			String resource, QueryParameters parameters, BODY body,
			ParameterizedTypeReference<Envelope<RESPONSE>> responseType, Object... uriVariables) {
		HttpEntity<BODY> request = createHttpEntity(body);
		String url = buildUrl(accessToken, resource, parameters, uriVariables);
		ResponseEntity<Envelope<RESPONSE>> response = restTemplate.exchange(url, method, request, responseType);

		Envelope<RESPONSE> envelope = extractEnvelope(response);
		return envelope;
	}

	private <RESPONSE> Envelope<RESPONSE> extractEnvelope(ResponseEntity<Envelope<RESPONSE>> response) {
		Envelope<RESPONSE> envelope = response.getBody();

		ResponseHeaders responseHeaders = new ResponseHeaders(response.getHeaders());
		envelope.setResponseHeaders(responseHeaders);

		return envelope;
	}

	private <BODY> HttpEntity<BODY> createHttpEntity(BODY body) {
		HttpHeaders headers = new HttpHeaders();

		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		return new HttpEntity<BODY>(body, headers);
	}

	private String buildUrl(String accessToken, String resource, QueryParameters parameters,
			Object... uriVariables) {
		UriComponentsBuilder builder =
				UriComponentsBuilder
						.fromHttpUrl(INSTAGRAM_API_URL)
						.pathSegment(resource)
						.queryParam(PARAMETER_ACCESS_TOKEN, accessToken);

		if (parameters != null) {
			for (Entry<String, String> parameterEntry : parameters.getParameters().entrySet()) {
				builder.queryParam(parameterEntry.getKey(), parameterEntry.getValue());
			}
		}

		return builder.buildAndExpand(uriVariables).toUriString();
	}

}
