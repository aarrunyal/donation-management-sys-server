package com.donationmanagementsystem.entity;

import java.time.LocalDate;

import com.donationmanagementsystem.utils.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private LocalDate donatedAt;
    
    @Column(nullable = false)
    private Long organisedFor;

    @ManyToOne
    @JoinColumn(name="organiser_id")
    private User organiser;

    @ManyToOne
    @JoinColumn(name="donation_id")
    private Donation donation;
}
