package com.donationmanagementsystem.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserAddressResponse {
	public Long id;
	
	@JsonProperty("address_line_1")
	public String addressLine1;

	@JsonProperty("address_line_2")
	public String addressLine2;

	public String city;
	public String state;
	public String postalCode;
	public String country;
}
