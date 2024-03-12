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
import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.payload.request.DonationPaymentRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationPaymentResponse;
import com.donationmanagementsystem.payload.response.DonationResponse;
import com.donationmanagementsystem.repository.DonationPaymentRepository;
import com.donationmanagementsystem.repository.DonationRepository;
import com.donationmanagementsystem.repository.UserRepository;
import com.donationmanagementsystem.service.DonationPaymentService;
import com.donationmanagementsystem.utils.DonationStatus;
import com.donationmanagementsystem.utils.Helper;
import com.donationmanagementsystem.utils.ResponseMessage;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonationPaymentServiceImpl implements DonationPaymentService {

    private final DonationPaymentRepository donationPaymentRepository;

    private final DonationRepository donationRepository;

    private final UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<DonationPaymentResponse> create(DonationPaymentRequest donationPaymentRequest) {
        try {
            DonationPayment donationPayment = this.modelMapper.map(donationPaymentRequest, DonationPayment.class);
            if (donationPaymentRequest.getDonerId() != null) {
                User user = userRepository.findById(donationPaymentRequest.getDonerId()).orElseThrow(() -> {
                    throw new ResourceNotFoundException("User", "id", donationPaymentRequest.getDonerId());
                });
                donationPayment.setDoner(user);
            }

            if (donationPaymentRequest.getDonationId() != null) {
                Donation donation = donationRepository.findById(donationPaymentRequest.getDonationId())
                        .orElseThrow(() -> {
                            throw new ResourceNotFoundException("Donation", "id", donationPaymentRequest.getDonerId());
                        });
                donationPayment.setDonation(donation);
            }
            donationPayment.setStatus(DonationStatus.PENDING);

            DonationPayment newDonationPayment = donationPaymentRepository.save(donationPayment);
            DonationPaymentResponse donationResponse = this.modelMapper.map(newDonationPayment,
                    DonationPaymentResponse.class);
            return new ResponseEntity<>(donationResponse, HttpStatus.OK);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ResponseEntity<DonationPaymentResponse> update(DonationPaymentRequest donationPaymentRequest,
            Long donationPaymentId) {
        try {
            DonationPayment savedDonationPayment = donationPaymentRepository.findById(donationPaymentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Donation Payment", "id", donationPaymentId));

            savedDonationPayment.setTransactionId(donationPaymentRequest.getTransactionId());
            savedDonationPayment.setPaymentMethod(donationPaymentRequest.getPaymentMethod());
            savedDonationPayment.setAmountDonated(donationPaymentRequest.getAmountDonated());
            savedDonationPayment.setDonatedAt(LocalDate.now());
            savedDonationPayment.setStatus(donationPaymentRequest.getStatus());
            DonationPayment updatedDonationPayment = donationPaymentRepository.save(savedDonationPayment);
            DonationPaymentResponse donationResponse = this.modelMapper.map(updatedDonationPayment,
                    DonationPaymentResponse.class);
            return new ResponseEntity<>(donationResponse, HttpStatus.OK);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ResponseEntity<DonationPaymentResponse> show(Long donationPaymentId) {
        DonationPayment savedDonationPayment = donationPaymentRepository.findById(donationPaymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Donation Payment", "id", donationPaymentId));
        return new ResponseEntity<>(this.modelMapper.map(savedDonationPayment, DonationPaymentResponse.class),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DonationPaymentResponse>> all() {
        List<DonationPayment> donationPayments = donationPaymentRepository.findAll(Helper.sortByAsc("id", "DESC"));
        List<DonationPaymentResponse> donationPaymentResponses = donationPayments
                .stream()
                .map(
                        (payment) -> this.modelMapper
                                .map(payment, DonationPaymentResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<List<DonationPaymentResponse>>(donationPaymentResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long donationPaymentId) {
        try {
            DonationPayment savedDonationPayment = donationPaymentRepository.findById(donationPaymentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Donation Payment", "id", donationPaymentId));
            if (savedDonationPayment != null) {
                donationPaymentRepository.delete(savedDonationPayment);
            }
            return ResponseMessage.ok("Donation deleted successfully");
        } catch (Exception ex) {
            return ResponseMessage.internalServerError("");
        }
    }

}
