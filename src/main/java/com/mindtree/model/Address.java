package com.mindtree.model;

import java.util.UUID;

public class Address {

	String id;
	String streetName;
	String city;
	String pincode;

	public Address() {
		this.id = UUID.randomUUID().toString();
	}

	public Address(String id, String streetName, String city, String pincode) {
		super();
		this.id = id;
		this.streetName = streetName;
		this.city = city;
		this.pincode = pincode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
