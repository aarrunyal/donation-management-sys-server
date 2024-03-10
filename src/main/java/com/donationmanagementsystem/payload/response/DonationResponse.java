package com.donationmanagementsystem.payload.response;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.utils.AppConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@PropertySource("classpath:application.properties")
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

    private String image;

    private String imagePath = AppConstant.UPLOAD_ROOT_PATH + "/donation";

}
