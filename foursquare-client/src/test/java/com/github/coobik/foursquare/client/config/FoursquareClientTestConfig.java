package com.github.coobik.foursquare.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan({ "com.github.coobik.api.client.rest", "com.github.coobik.foursquare.client" })
public class FoursquareClientTestConfig {

	@Value("${client_id}")
	private String clientId;

	@Value("${secret}")
	private String secret;

	@Value("${access_token}")
	private String accessToken;

	@Value("${redirect_uri}")
	private String redirectUri;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public String getClientId() {
		return clientId;
	}

	public String getSecret() {
		return secret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

}
