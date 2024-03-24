package com.donationmanagementsystem.controller;



import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.DonationRequest;
import com.donationmanagementsystem.payload.response.DonationResponse;
import com.donationmanagementsystem.payload.response.ReportResponse;
import com.donationmanagementsystem.service.DonationService;
import com.donationmanagementsystem.service.UserService;

import jakarta.validation.Valid;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping(value="/api/v1/admin/report")
public class ReportController {

  @Autowired
  UserService userService;

  @Autowired
  DonationService donationService;

  @PostMapping
    public String post(
        @Valid 
        @RequestBody DonationRequest donationRequest) {
        User user = userService.getLoggedInUser();
        // return donationService.create(donationRequest, user);
        return "hello";
    }

  

}
