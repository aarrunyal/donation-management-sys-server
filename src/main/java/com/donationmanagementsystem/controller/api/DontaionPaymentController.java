package com.donationmanagementsystem.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.DonationPaymentRequest;
import com.donationmanagementsystem.payload.request.DonationPaymentUpdateRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationPaymentResponse;
import com.donationmanagementsystem.payload.response.PaymentIntentResponse;
import com.donationmanagementsystem.service.DonationPaymentService;
import com.donationmanagementsystem.service.UserService;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/admin/donation-payment")
@CacheConfig(cacheNames = "donation_payments")
public class DontaionPaymentController {

    @Autowired
    private DonationPaymentService donationPaymentService;

    @Autowired
    private UserService userService;

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
    @Cacheable(key = "#id")
    public ResponseEntity<DonationPaymentResponse> show(@PathVariable Long id) {
        return donationPaymentService.show(id);
    }

    @GetMapping("")
    public ResponseEntity<List<DonationPaymentResponse>> all() {
        return donationPaymentService.all();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", beforeInvocation = true)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return this.donationPaymentService.delete(id);
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<PaymentIntentResponse> createPaymentIntent(
            @Valid @RequestBody DonationPaymentRequest donationPaymentRequest) {
        DonationPaymentResponse donationPaymentResponse = donationPaymentService.create(donationPaymentRequest);
        return donationPaymentService.createPaymentIntent(donationPaymentResponse);
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<DonationPaymentResponse>> getByUser(@RequestParam Long size) {
        User user = userService.getLoggedInUser();
        return donationPaymentService.getByUser(user, size);
    }

    @GetMapping("/by-user/total")
    public ResponseEntity<Map<String, String>> totalDonatedByUser() {
        User user = userService.getLoggedInUser();
        return donationPaymentService.totalDonatedByUser(user);
    }

}
