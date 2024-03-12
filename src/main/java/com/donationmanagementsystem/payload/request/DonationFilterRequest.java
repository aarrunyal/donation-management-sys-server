package com.donationmanagementsystem.payload.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationFilterRequest {

    private String name;

    private String description;

    private LocalDate eventDate;

    private Long expectedCollection;

    private boolean expired;

    private boolean verified;

    private boolean status;

}
