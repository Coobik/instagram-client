package com.github.coobik.api.client.rest;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Component
public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -1100087492544093257L;

	public CustomObjectMapper() {
		registerModule(new JodaModule());

		setSerializationInclusion(Include.NON_NULL);

		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

		configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		configure(SerializationFeature.INDENT_OUTPUT, true);
		configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
		configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
		configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false);
	}

}
