package com.donationmanagementsystem.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.donationmanagementsystem.utils.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="invoices")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends BaseEntity {
    
    @Column(nullable = false)
    private Long invoiceNo;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String email;

    private String phoneNumber;

    private String billingAddress;

    private Long donationAmount;

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
