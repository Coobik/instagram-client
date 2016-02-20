package com.github.coobik.instagram.client.query;

public class LocationParameters extends GeoParameters {

	public LocationParameters() {

	}

	public LocationParameters(double latitude, double longitude, int distanceMeters) {
		super(latitude, longitude, distanceMeters);
	}

	public void setFacebookPlacesId(String facebookPlacesId) {
		setParameter("facebook_places_id", facebookPlacesId);
	}

	/**
	 * @deprecated use {@link #setFoursquareV2Id(String)}
	 */
	@Deprecated
	public void setFoursquareId(String foursquareId) {
		setParameter("foursquare_id", foursquareId);
	}

	public void setFoursquareV2Id(String foursquareV2Id) {
		setParameter("foursquare_v2_id", foursquareV2Id);
	}

}
