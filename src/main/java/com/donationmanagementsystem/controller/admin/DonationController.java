package com.donationmanagementsystem.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.DonationRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationResponse;
import com.donationmanagementsystem.service.DonationService;
import com.donationmanagementsystem.service.UserService;

import jakarta.validation.Valid;

@RestController("AdminDonationController")
@RequestMapping(value = "/api/v1/admin/donation")
@PreAuthorize("hasRole('ADMIN')")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> post(@Valid @RequestBody DonationRequest donationRequest) {
        User user = userService.getLoggedInUser();
        return donationService.create(donationRequest, user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody DonationRequest donationRequest,
            @PathVariable Long id) {
        return donationService.update(donationRequest, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationResponse> show(@PathVariable Long id) {
        return donationService.show(id);
    }

    @GetMapping("")
    public ResponseEntity<List<DonationResponse>> all() {
        return donationService.all();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return this.donationService.delete(id);
    }

    @GetMapping("/{id}/{status}")
    public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id, @PathVariable String status) {
        return this.donationService.toggleStatus(id, status);
    }
}
