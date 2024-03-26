package com.donationmanagementsystem.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.payload.request.DonationRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationResponse;
import com.donationmanagementsystem.service.DonationService;
import com.donationmanagementsystem.service.UserService;

import jakarta.validation.Valid;

@RestController("AdminDonationController")
@RequestMapping(value = "/api/v1/admin/donation")
@CacheConfig(cacheNames = "donations")
// @PreAuthorize("hasRole('ADMIN')")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<DonationResponse> post(
            @Valid @RequestBody DonationRequest donationRequest) {
        User user = userService.getLoggedInUser();
        return donationService.create(donationRequest, user);
    }

    @PostMapping(value = "/{id}/upload", consumes = { "multipart/form-data" })
    public ResponseEntity<ApiResponse> uploadImage(
            @PathVariable(value = "id") Long id,
            @RequestPart MultipartFile file) {
        return donationService.uploadImage(id, file);
    }

    @PutMapping("/{id}")
    @CachePut(key = "#id")
    public ResponseEntity<DonationResponse> update(@Valid @RequestBody DonationRequest donationRequest,
            @PathVariable Long id) {
        return donationService.update(donationRequest, id);
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public ResponseEntity<DonationResponse> show(@PathVariable Long id) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Fetching from database :" + id);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        return donationService.show(id);
    }

    @GetMapping("")
    public ResponseEntity<List<DonationResponse>> all() {
        return donationService.all();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", beforeInvocation = true)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return this.donationService.delete(id);
    }

    @GetMapping("/{id}/{status}")
    public ResponseEntity<ApiResponse> toggleStatus(@PathVariable Long id, @PathVariable String status) {
        return this.donationService.toggleStatus(id, status);
    }

    @GetMapping("/{id}/randomly/{size}")
    public ResponseEntity<List<DonationResponse>> getOtherCampaignRandomly(@PathVariable Long id,
            @PathVariable int size) {
        return this.donationService.getOtherCampaignRandomly(id, size);
    }

}
