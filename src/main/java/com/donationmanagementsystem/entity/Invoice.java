package com.donationmanagementsystem.entity;

import com.donationmanagementsystem.utils.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="invoices")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends BaseEntity {
    
    @Column(nullable = false)
    private String invoiceNo;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    private String billingAddress;

    private String donationAmount;

    @Column(nullable = false)
    private Long sutTotal;

    private Long tax;

    @Column(nullable = false)
    private Long total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="donation_payment_id")
    private DonationPayment donationPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doner_id")
    private User doner;

    
}
