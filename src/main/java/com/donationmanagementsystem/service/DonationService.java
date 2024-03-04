package com.donationmanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.DonationRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationResponse;

public interface DonationService {

    ResponseEntity<ApiResponse> create(DonationRequest donationRequest, User user);

    ResponseEntity<ApiResponse> create(DonationRequest donationRequest, User user, MultipartFile file);

    ResponseEntity<ApiResponse> update(DonationRequest donationRequest, Long donationId);

    ResponseEntity<DonationResponse> show(Long donationId);

    ResponseEntity<List<DonationResponse>> all();

    ResponseEntity<ApiResponse> delete(Long donationId);

    ResponseEntity<ApiResponse> toggleStatus(Long id, String status);
}
