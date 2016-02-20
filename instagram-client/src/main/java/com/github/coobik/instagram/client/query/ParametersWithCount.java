package com.github.coobik.instagram.client.query;

public class ParametersWithCount extends QueryParameters {

	public ParametersWithCount() {

	}

	public ParametersWithCount(long count) {
		setCount(count);
	}

	public void setCount(long count) {
		setParameter("count", String.valueOf(count));
	}

}
