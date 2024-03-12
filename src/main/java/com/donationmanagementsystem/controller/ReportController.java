package com.donationmanagementsystem.controller;

import org.springframework.http.ResponseEntity;

import com.donationmanagementsystem.payload.response.ReportResponse;
import org.springframework.web.bind.annotation.GetMapping;


public class ReportController {

  @GetMapping(value="/report")
  public ResponseEntity<ReportResponse> reposEntityBuilder(){
    return null;

  }

}
