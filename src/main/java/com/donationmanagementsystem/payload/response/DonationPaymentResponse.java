package com.donationmanagementsystem.payload.response;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.donationmanagementsystem.entity.Donation;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.utils.DonationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationPaymentResponse {

    private Long id;

    private String transactionId;

    private String paymentMethod;

    private Long amountDonated;

    private LocalDate donatedAt;

    private Long organisedFor;

    private Donation donation;

    private User doner;

    private DonationStatus status;

}
