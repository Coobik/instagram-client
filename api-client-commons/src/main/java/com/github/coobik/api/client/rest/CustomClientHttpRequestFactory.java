package com.github.coobik.api.client.rest;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;

/**
 * Allow all SSL certificates
 */
@Component
public class CustomClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

	private final HostnameVerifier hostnameVerifier = new CustomHostnameVerifier();

	public CustomClientHttpRequestFactory() {
		setBufferRequestBody(false);
	}

	@Override
	protected void prepareConnection(HttpURLConnection connection, String httpMethod)
			throws IOException {
		if (connection instanceof HttpsURLConnection) {
			((HttpsURLConnection) connection).setHostnameVerifier(hostnameVerifier);
		}

		super.prepareConnection(connection, httpMethod);
	}

	private static class CustomHostnameVerifier implements HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {
			// allow all SSL certificates
			return true;
		}

	}

}
