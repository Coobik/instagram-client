package com.github.coobik.foursquare.client.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.github.coobik.foursquare.client.model.FoursquareResponse;
import com.github.coobik.foursquare.client.model.VenueResponse;
import com.github.coobik.foursquare.client.query.FoursquareAuth;
import com.github.coobik.foursquare.client.rest.FoursquareRestClient;
import com.google.common.base.Preconditions;

@Service
public class FoursquareVenueService {

	private static final String PATH_VENUES_ID = "venues/{venue_id}";

	private static final ParameterizedTypeReference<FoursquareResponse<VenueResponse>> TYPE_VENUE =
			new ParameterizedTypeReference<FoursquareResponse<VenueResponse>>() {
			};

	@Autowired
	private FoursquareRestClient restClient;

	public FoursquareResponse<VenueResponse> getVenue(FoursquareAuth auth, String venueId) {
		Preconditions.checkNotNull(auth, "auth");
		Preconditions.checkArgument(StringUtils.isNotBlank(venueId), "venueId");

		FoursquareResponse<VenueResponse> venue =
				restClient.getObject(auth, PATH_VENUES_ID, TYPE_VENUE, venueId);

		return venue;
	}

}
