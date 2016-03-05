package com.github.coobik.api.client.rest;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

@Component
public class CustomRestTemplate extends RestTemplate {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ClientHttpRequestFactory clientHttpRequestFactory;

	@PostConstruct
	public void init() {
		setRequestFactory(clientHttpRequestFactory);

		MappingJackson2HttpMessageConverter messageConverter =
				new MappingJackson2HttpMessageConverter(objectMapper);
		StringHttpMessageConverter stringMessageConverter = new StringHttpMessageConverter();

		List<HttpMessageConverter<?>> messageConverters =
				Lists.newArrayList(messageConverter, stringMessageConverter);
		setMessageConverters(messageConverters);
	}

}
