package com.github.coobik.instagram.client.query;

public class LikeParameters extends ParametersWithCount {

	public void setMinLikeId(String minLikeId) {
		setParameter("min_like_id", minLikeId);
	}

	public void setMaxLikeId(String maxLikeId) {
		setParameter("max_like_id", maxLikeId);
	}

}
