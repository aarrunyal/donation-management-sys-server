package com.donationmanagementsystem.payload.response;

import java.time.LocalDate;

import lombok.Data;

@Data

public class UserSettingResponse {

	public String contactNo;
	public LocalDate dob;
	public String gender;
	public String alternativeContactNo;
}
