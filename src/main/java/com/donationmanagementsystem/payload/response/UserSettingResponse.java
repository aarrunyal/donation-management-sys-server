package com.donationmanagementsystem.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class UserSettingResponse {
	public Long id;
	public String contactNo;
	public String alternativeContactNo;
}
