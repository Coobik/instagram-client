package com.github.coobik.instagram.client.rest;

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
public class InstagramClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

	private final HostnameVerifier hostnameVerifier = new InstagramHostnameVerifier();

	public InstagramClientHttpRequestFactory() {
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

	private static class InstagramHostnameVerifier implements HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {
			// allow all SSL certificates
			return true;
		}

	}

}
