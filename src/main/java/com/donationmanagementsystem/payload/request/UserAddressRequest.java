package com.donationmanagementsystem.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressRequest {
	
	@NotBlank
	@NotNull
	@JsonProperty("address_line_1")
	public String addressLine1;
	
	@JsonProperty("address_line_2")
	public String addressLine2;

	@NotBlank
	@NotNull
	public String city;

	@NotBlank
	@NotNull
	public String state;

	@NotBlank
	@NotNull
	public String postalCode;

	@NotBlank
	@NotNull
	public String country;
}
