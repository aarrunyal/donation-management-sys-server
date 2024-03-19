package com.donationmanagementsystem.controller;



import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.DonationRequest;
import com.donationmanagementsystem.payload.response.DonationResponse;
import com.donationmanagementsystem.payload.response.ReportResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// @RequestMapping(value="/api/test/Report")
// public class ReportController {

//   @PostMapping
//     public Respon seEntity<DonationResponse> post(
//         @Valid @RequestBody DonationRequest donationRequest) {
//         User user = userService.getLoggedInUser();
//         return donationService.create(donationRequest, user);
//     }

  

// }
