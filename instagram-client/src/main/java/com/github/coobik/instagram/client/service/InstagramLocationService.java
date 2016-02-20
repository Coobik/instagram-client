package com.github.coobik.instagram.client.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.coobik.instagram.client.model.Envelope;
import com.github.coobik.instagram.client.model.Location;
import com.github.coobik.instagram.client.model.Media;
import com.github.coobik.instagram.client.query.IdParameters;
import com.github.coobik.instagram.client.query.LocationParameters;
import com.github.coobik.instagram.client.rest.InstagramRestClient;
import com.google.common.base.Preconditions;

@Service
public class InstagramLocationService {

	private static final String PATH_LOCATIONS_ID = "locations/{location_id}";
	private static final String PATH_LOCATIONS_ID_MEDIA_RECENT = PATH_LOCATIONS_ID + "/media/recent";
	private static final String PATH_LOCATIONS_SEARCH = "locations/search";

	@Autowired
	private InstagramRestClient restClient;

	public Envelope<Location> getLocation(String accessToken, String locationId) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(locationId), "locationId");

		Envelope<Location> locationEnvelope =
				restClient.getObject(
						accessToken, PATH_LOCATIONS_ID, null, TypeReference.TYPE_LOCATION, locationId);

		return locationEnvelope;
	}

	public Envelope<List<Media>> listMedia(String accessToken, String locationId, IdParameters parameters) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkArgument(StringUtils.isNotBlank(locationId), "locationId");

		Envelope<List<Media>> mediaListEnvelope =
				restClient.getObject(
						accessToken, PATH_LOCATIONS_ID_MEDIA_RECENT, parameters, TypeReference.TYPE_MEDIA_LIST,
						locationId);

		return mediaListEnvelope;
	}

	public Envelope<List<Location>> searchLocations(String accessToken, LocationParameters parameters) {
		Preconditions.checkArgument(StringUtils.isNotBlank(accessToken), "accessToken");
		Preconditions.checkNotNull(parameters, "parameters");

		Envelope<List<Location>> locationsListEnvelope =
				restClient.getObject(accessToken, PATH_LOCATIONS_SEARCH, parameters, TypeReference.TYPE_LOCATIONS_LIST);

		return locationsListEnvelope;
	}

}
