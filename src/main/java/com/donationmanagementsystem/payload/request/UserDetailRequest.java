package com.donationmanagementsystem.payload.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailRequest {

    @NotBlank
    @NotNull
    @JsonProperty("address_line_1")
    public String addressLine1;

    @JsonProperty("address_line_2")
    public String addressLine2;

    @NotNull
    @JsonProperty("city")
    public String city;

    @NotNull
    @JsonProperty("country")
    public String country;

    @NotNull
    @JsonProperty("contact_no")
    public String contactNo;

    @Past(message="Date must be from past")
    @NotNull
    @JsonProperty("dob")
    public LocalDate dob;

    @NotNull
    public String gender;

    @NotNull
    @JsonProperty("postal_code")
    public String postalCode;

    @NotNull
    public String state;

}
