package com.github.coobik.instagram.client.query;

public class SearchParameters extends ParametersWithCount {

	public SearchParameters(String query) {
		setQuery(query);
	}

	public SearchParameters(long count, String query) {
		super(count);

		setQuery(query);
	}

	public void setQuery(String query) {
		setParameter("q", query);
	}

}
