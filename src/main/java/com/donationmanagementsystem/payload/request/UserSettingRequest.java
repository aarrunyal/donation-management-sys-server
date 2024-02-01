package com.donationmanagementsystem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSettingRequest {
	public Long id;
	public String contactNo;
	public String alternativeContactNo;
}
