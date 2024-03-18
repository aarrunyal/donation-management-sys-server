package com.donationmanagementsystem.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.payload.request.DonationPaymentRequest;
import com.donationmanagementsystem.payload.request.DonationPaymentUpdateRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationPaymentResponse;
import com.donationmanagementsystem.payload.response.PaymentIntentResponse;
import com.donationmanagementsystem.service.DonationPaymentService;
import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/admin/donation-payment")
public class DontaionPaymentController {

    @Autowired
    private DonationPaymentService donationPaymentService;

    @PostMapping
    public DonationPaymentResponse post(
            @Valid @RequestBody DonationPaymentRequest donationPaymentRequest) {
        return donationPaymentService.create(donationPaymentRequest);
    }

    @PutMapping("/{checkoutToken}")
    public ResponseEntity<ApiResponse> update(
            @Valid @RequestBody DonationPaymentUpdateRequest donationPaymentRequest,
            @PathVariable String checkoutToken) {
        return donationPaymentService.update(donationPaymentRequest, checkoutToken);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationPaymentResponse> show(@PathVariable Long id) {
        return donationPaymentService.show(id);
    }

    @GetMapping("")
    public ResponseEntity<List<DonationPaymentResponse>> all() {
        return donationPaymentService.all();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return this.donationPaymentService.delete(id);
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<PaymentIntentResponse> createPaymentIntent(
            @Valid @RequestBody DonationPaymentRequest donationPaymentRequest) {
        DonationPaymentResponse donationPaymentResponse = donationPaymentService.create(donationPaymentRequest);
        return donationPaymentService.createPaymentIntent(donationPaymentResponse);
    }

}
