package com.github.coobik.instagram.client.query;

public class TagParameters extends ParametersWithCount {

	public TagParameters(String minTagId, String maxTagId) {
		setMinTagId(minTagId);
		setMaxTagId(maxTagId);
	}

	public TagParameters(long count) {
		this(null, null, count);
	}

	public TagParameters(String minTagId, String maxTagId, long count) {
		super(count);

		setMinTagId(minTagId);
		setMaxTagId(maxTagId);
	}

	public void setMinTagId(String minTagId) {
		setParameter("min_tag_id", minTagId);
	}

	public void setMaxTagId(String maxTagId) {
		setParameter("max_tag_id", maxTagId);
	}

}
