package com.github.coobik.api.client.query;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

public class QueryParameters {

	private final Map<String, String> parameters = Maps.newLinkedHashMap();

	public Map<String, String> getParameters() {
		return parameters;
	}

	public QueryParameters setParameter(String name, String value) {
		Preconditions.checkArgument(StringUtils.isNotBlank(name), "name");

		value = ((value == null) ? ("") : (value.trim()));
		parameters.put(name.trim(), value);

		return this;
	}

}
