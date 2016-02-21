package com.github.coobik.instagram.client.query;

import com.github.coobik.instagram.client.model.Pagination;

public class CursorParameters extends ParametersWithCount {

	public CursorParameters(String cursor) {
		setCursor(cursor);
	}

	public CursorParameters(String cursor, long count) {
		super(count);

		setCursor(cursor);
	}

	public void setCursor(String cursor) {
		setParameter("cursor", cursor);
	}

	public void setPagination(Pagination pagination) {
		if (pagination == null) {
			return;
		}

		setCursor(pagination.getNextCursor());
	}

}
