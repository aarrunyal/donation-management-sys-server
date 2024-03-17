package com.donationmanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

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
}
