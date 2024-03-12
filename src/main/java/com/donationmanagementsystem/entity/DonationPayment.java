package com.donationmanagementsystem.entity;

import java.time.LocalDate;

import com.donationmanagementsystem.utils.BaseEntity;
import com.donationmanagementsystem.utils.DonationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="donation_payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationPayment extends BaseEntity{

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private String paymentMethod;
    
    @Column(nullable = false)
    private Long amountDonated;
    
    
    @Column(nullable = false)
    private LocalDate donatedAt;
    
    @Column(nullable = false)
    private Long organisedFor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="donation_id")
    private Donation donation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="donated_by")
    private User doner;

    @Enumerated(EnumType.STRING)
    private DonationStatus status;


}
