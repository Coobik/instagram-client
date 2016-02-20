package com.github.coobik.instagram.client.query;

public class IdParameters extends ParametersWithCount {

	public void setMinId(String minId) {
		setParameter("min_id", minId);
	}

	public void setMaxId(String maxId) {
		setParameter("max_id", maxId);
	}

}
