package com.donationmanagementsystem.payload.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSettingRequest {

	@NotBlank(message = "Contact Number is empty")
    @NotNull(message = "Contact Number cannot be null")
	@Size(min = 10, max = 10, message = "Contact number should be 10 digits only")
	public String contactNo;

	@Column(nullable = true)
	@Size(min = 10, max = 10, message = "Contact number should be 10 digits only")
	public String alternativeContactNo;
}
