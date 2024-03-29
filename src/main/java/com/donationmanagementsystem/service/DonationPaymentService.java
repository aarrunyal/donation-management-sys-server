package com.donationmanagementsystem.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.DonationPaymentRequest;
import com.donationmanagementsystem.payload.request.DonationPaymentUpdateRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationPaymentResponse;
import com.donationmanagementsystem.payload.response.PaymentIntentResponse;

public interface DonationPaymentService {

        DonationPaymentResponse create(
                        DonationPaymentRequest donationPaymentRequest);

        ResponseEntity<ApiResponse> update(
                        DonationPaymentUpdateRequest donationPaymentRequest,
                        String checkoutToken);

        ResponseEntity<DonationPaymentResponse> show(Long donationId);

        ResponseEntity<List<DonationPaymentResponse>> all();

        ResponseEntity<ApiResponse> delete(Long donationId);

        ResponseEntity<PaymentIntentResponse> createPaymentIntent(DonationPaymentResponse donationPaymentResponse);

        public ResponseEntity<List<DonationPaymentResponse>> getByUser(User user, Long size);

        ResponseEntity<Map<String, String>> totalDonatedByUser(User user);
}
