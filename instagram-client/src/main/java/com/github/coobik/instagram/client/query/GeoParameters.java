package com.github.coobik.instagram.client.query;

public class GeoParameters extends ParametersWithCount {

	public GeoParameters() {

	}

	public GeoParameters(double latitude, double longitude, int distanceMeters) {
		setLatitude(latitude);
		setLongitude(longitude);
		setDistance(distanceMeters);
	}

	public void setLatitude(double latitude) {
		setParameter("lat", String.valueOf(latitude));
	}

	public void setLongitude(double longitude) {
		setParameter("lng", String.valueOf(longitude));
	}

	/**
	 * Default is 1000 m (distance = 1000), max distance is 5000 m
	 */
	public void setDistance(int distanceMeters) {
		setParameter("distance", String.valueOf(distanceMeters));
	}

}
