package com.donationmanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.DonationPaymentRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationPaymentResponse;

public interface DonationPaymentService {

        ResponseEntity<DonationPaymentResponse> create(
                        DonationPaymentRequest donationPaymentRequest);

        ResponseEntity<DonationPaymentResponse> update(
                        DonationPaymentRequest donationPaymentRequest,
                        Long donationId);

        ResponseEntity<DonationPaymentResponse> show(Long donationId);

        ResponseEntity<List<DonationPaymentResponse>> all();

        ResponseEntity<ApiResponse> delete(Long donationId);
}
