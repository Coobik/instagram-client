package com.github.coobik.instagram.client.rest;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class InstagramTimeDeserializer extends JsonDeserializer<DateTime> {

	@Override
	public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String timeString = p.getText();

		if (StringUtils.isBlank(timeString)) {
			return null;
		}

		long timeSeconds = Long.valueOf(timeString);
		DateTime time = new DateTime(timeSeconds * 1000L);

		return time;
	}

}
