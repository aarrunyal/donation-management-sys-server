package com.donationmanagementsystem.payload.request;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequest {

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @NotNull(message = "Description cannot be blank")
    private String description;

    // @NotBlank(message = "cannot be blank")
    @Future(message = "Event date must be a future date")
    @NotNull(message = "Event date cannot be blank")
    @JsonProperty("event_date")
    private LocalDate eventDate;

    @NotNull(message = "Exepected collection cannot be blank")
    @JsonProperty("expected_collection")
    private Long expectedCollection;


    private Long organisedFor;

    private boolean expired;

    private boolean verified;


    private boolean status;


    private String image=null;


}
