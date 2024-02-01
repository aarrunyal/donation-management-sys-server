package com.donationmanagementsystem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressRequest {
	public Long id;
	public String addressLine1;
	public String addressLine2;
	public String city;
	public String state;
	public String postalCode;
}
