package com.github.coobik.instagram.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;

@Component
public class InstagramClientTestHelper {

	@Autowired
	private ObjectMapper objectMapper;

	public void printJson(Object entity) throws JsonProcessingException {
		Preconditions.checkNotNull(entity, "entity");

		String json = objectMapper.writeValueAsString(entity);
		System.out.println(json);
	}

}
