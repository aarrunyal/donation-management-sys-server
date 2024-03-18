package com.donationmanagementsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.donationmanagementsystem.entity.Donation;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.exception.ResourceNotFoundException;

import com.donationmanagementsystem.payload.request.DonationRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationResponse;
import com.donationmanagementsystem.repository.DonationRepository;
import com.donationmanagementsystem.service.DonationService;
import com.donationmanagementsystem.service.StorageService;
import com.donationmanagementsystem.utils.Helper;
import com.donationmanagementsystem.utils.ResponseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private static final String UPLOAD_PATH = "donation";

    private final DonationRepository donationRepository;

    private final StorageService storageService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<DonationResponse> create(DonationRequest donationRequest, User user) {
        try {
            Donation donation = this.modelMapper.map(donationRequest, Donation.class);
            donation.setOrganiser(user);
            Donation newDonation = donationRepository.save(donation);
            DonationResponse donationResponse = this.modelMapper.map(newDonation, DonationResponse.class);
            return new ResponseEntity<>(donationResponse, HttpStatus.OK);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ResponseEntity<ApiResponse> uploadImage(Long id, MultipartFile file) {
        try {
            Donation savedDonation = donationRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", id));
            if (!file.isEmpty()) {
                if (savedDonation.getImage() != null) {
                    storageService.deleteFile(UPLOAD_PATH, savedDonation.getImage());
                }
                var imageName = storageService.uploadFile(file, UPLOAD_PATH);
                savedDonation.setImage(imageName);
                donationRepository.save(savedDonation);
                return ResponseMessage.ok(null);
            }
            return ResponseMessage.internalServerError(null);
        } catch (Exception ex) {
            return ResponseMessage.internalServerError(null);
        }
    }

    @Override
    public ResponseEntity<DonationResponse> update(DonationRequest donationRequest, Long donationId) {
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
            Donation updateDonation = donationRepository.save(savedDonation);
            DonationResponse donationResponse = this.modelMapper.map(updateDonation, DonationResponse.class);
            return new ResponseEntity<>(donationResponse, HttpStatus.OK);
        } catch (Exception ex) {
            return null;
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
        List<Donation> donations = donationRepository.findAll(Helper.sortByAsc("id", "DESC"));
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
                if (savedDonation.getImage() != null) {
                    storageService.deleteFile(UPLOAD_PATH, savedDonation.getImage());
                }
                donationRepository.delete(savedDonation);
            }
            return ResponseMessage.ok("Donation deleted successfully");
        } catch (Exception ex) {
            return ResponseMessage.internalServerError("");
        }
    }

    @Override
    public ResponseEntity<ApiResponse> toggleStatus(Long id, String status) {
        try {
            Donation savedDonation = donationRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", id));
            if (status.equals("expired") || status.equals("not-expired"))
                savedDonation.setExpired(!savedDonation.isExpired());
            else if (status.equals("active") || status.equals("in-active"))
                savedDonation.setStatus(!savedDonation.isStatus());
            else
                savedDonation.setVerified(!savedDonation.isVerified());
            donationRepository.save(savedDonation);
            return ResponseMessage.ok(null);
        } catch (Exception ex) {
            return ResponseMessage.internalServerError("");
        }

    }

    @Override
    public ResponseEntity<List<DonationResponse>> getOtherCampaignRandomly(Long id, int size) {
        List<Donation> donations = donationRepository.findOtherCampaignRandomly(id, size);
        List<DonationResponse> donaitonResponses = donations
                .stream()
                .map(
                        (donation) -> this.modelMapper
                                .map(donation, DonationResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<List<DonationResponse>>(donaitonResponses, HttpStatus.OK);
    }

}
