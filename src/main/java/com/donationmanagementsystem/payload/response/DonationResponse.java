package com.donationmanagementsystem.payload.response;

import java.time.LocalDate;

import com.donationmanagementsystem.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationResponse {

    private Long id;

    private String name;

    private String description;

    private LocalDate eventDate;

    private Long expectedCollection;

    private Long organisedFor;

    private boolean expired;

    private boolean status;
    
    private boolean verified;

    @JsonIgnore
    private User organiser;
}
