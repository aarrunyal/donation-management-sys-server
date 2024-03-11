package com.donationmanagementsystem.payload.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DonationFilterRequest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class DonationRequest {

        
        private String name;

        private String description;

        private LocalDate eventDate;

        private Long expectedCollection;

        private boolean expired;

        private boolean verified;

        private boolean status;

    }
}
