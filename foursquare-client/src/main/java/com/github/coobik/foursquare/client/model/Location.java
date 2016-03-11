package com.github.coobik.foursquare.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

	private String address;
	private String crossStreet;

	private Double lat;
	private Double lng;

	/**
	 * distance in meters
	 */
	private Double distance;

	private String cc;

	private String city;
	private String state;
	private String country;
	private String postalCode;

	private List<String> formattedAddress;

	/**
	 * if true, then lat/lng have reduced precision
	 */
	private Boolean isFuzzed;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCrossStreet() {
		return crossStreet;
	}

	public void setCrossStreet(String crossStreet) {
		this.crossStreet = crossStreet;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<String> getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(List<String> formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public Boolean getIsFuzzed() {
		return isFuzzed;
	}

	public void setIsFuzzed(Boolean isFuzzed) {
		this.isFuzzed = isFuzzed;
	}

}
