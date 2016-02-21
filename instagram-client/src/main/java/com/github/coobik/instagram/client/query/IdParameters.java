package com.github.coobik.instagram.client.query;

import com.github.coobik.instagram.client.model.Pagination;

public class IdParameters extends ParametersWithCount {

	public void setMinId(String minId) {
		setParameter("min_id", minId);
	}

	public void setMaxId(String maxId) {
		setParameter("max_id", maxId);
	}

	public void setPagination(Pagination pagination) {
		if (pagination == null) {
			return;
		}

		setMaxId(pagination.getNextMaxId());
	}

}
