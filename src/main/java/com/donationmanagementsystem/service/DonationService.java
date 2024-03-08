package com.donationmanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.DonationRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationResponse;

public interface DonationService {

    ResponseEntity<DonationResponse> create(DonationRequest donationRequest, User user);

    ResponseEntity<ApiResponse> uploadImage(Long id, MultipartFile file);

    public ResponseEntity<DonationResponse> update(DonationRequest donationRequest, Long donationId);

    ResponseEntity<DonationResponse> show(Long donationId);

    ResponseEntity<List<DonationResponse>> all();

    ResponseEntity<ApiResponse> delete(Long donationId);

    ResponseEntity<ApiResponse> toggleStatus(Long id, String status);

    ResponseEntity<List<DonationResponse>> getOtherCampaignRandomly(Long id, int size);
}
