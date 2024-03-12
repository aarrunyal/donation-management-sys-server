package com.donationmanagementsystem.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.DonationPaymentRequest;
import com.donationmanagementsystem.payload.request.DonationRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationPaymentResponse;
import com.donationmanagementsystem.payload.response.DonationResponse;
import com.donationmanagementsystem.service.DonationPaymentService;
import com.donationmanagementsystem.service.DonationService;
import com.donationmanagementsystem.service.UserService;
import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/donation-payment")
public class DontaionPaymentController {

    @Autowired
    private DonationPaymentService donationPaymentService;


    @PostMapping
    public ResponseEntity<DonationPaymentResponse> post(
            @Valid @RequestBody DonationPaymentRequest donationPaymentRequest) {
        return donationPaymentService.create(donationPaymentRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonationPaymentResponse> update(
            @Valid @RequestBody DonationPaymentRequest donationPaymentRequest,
            @PathVariable Long id) {
        return donationPaymentService.update(donationPaymentRequest, id);
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

    

}
