package com.donationmanagementsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.entity.Donation;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.payload.request.DonationRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationResponse;
import com.donationmanagementsystem.repository.DonationRepository;
import com.donationmanagementsystem.service.DonationService;
import com.donationmanagementsystem.utils.ResponseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<ApiResponse> create(DonationRequest donationRequest, User user) {
        try {
            Donation donation = this.modelMapper.map(donationRequest, Donation.class);
            donation.setOrganiser(user);
            donationRepository.save(donation);
            return ResponseMessage.created("Donation campaign has been creeated successfully");
        } catch (Exception ex) {
            return ResponseMessage.internalServerError(null);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> update(DonationRequest donationRequest, Long donationId) {
        try {
            Donation savedDonation = donationRepository.findById(donationId)
                    .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", donationId));

            savedDonation.setName(donationRequest.getName());
            savedDonation.setDescription(donationRequest.getDescription());
            savedDonation.setEventDate(donationRequest.getEventDate());
            savedDonation.setExpectedCollection(donationRequest.getExpectedCollection());
            savedDonation.setExpired(donationRequest.isExpired());
            savedDonation.setVerified(donationRequest.isVerified());
            savedDonation.setStatus(donationRequest.isStatus());
            donationRepository.save(savedDonation);
            return ResponseMessage.ok("Donation campaign has been updated successfully");
        } catch (Exception ex) {
            return ResponseMessage.internalServerError(null);
        }
    }

    @Override
    public ResponseEntity<DonationResponse> show(Long donationId) {
        Donation savedDonation = donationRepository.findById(donationId)
                .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", donationId));
        return new ResponseEntity<>(this.modelMapper.map(savedDonation, DonationResponse.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DonationResponse>> all() {
        List<Donation> donations = donationRepository.findAll();
        List<DonationResponse> donaitonResponses = donations
                .stream()
                .map(
                        (donation) -> this.modelMapper
                                .map(donation, DonationResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<List<DonationResponse>>(donaitonResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long donationId) {
        try {
            Donation savedDonation = donationRepository.findById(donationId)
                    .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", donationId));
            if (savedDonation != null) {
                donationRepository.delete(savedDonation);
            }
            return ResponseMessage.ok("Donation deleted successfully");
        } catch (Exception ex) {
            return ResponseMessage.internalServerError("");
        }
    }

}
