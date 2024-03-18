package com.donationmanagementsystem.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.donationmanagementsystem.utils.BaseEntity;
import com.donationmanagementsystem.utils.DonationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "donation_payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationPayment extends BaseEntity {

    @Column(nullable = true)
    private String checkoutToken;

    @Column(nullable = true)
    private String transactionId;

    @Column(nullable = false)
    private String paymentMethod;

    private String currency;

    @Column(nullable = false)
    private Long amountDonated;

    private LocalDate donatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_id")
    private Donation donation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donated_by")
    private User doner;

    @Enumerated(EnumType.STRING)
    private DonationStatus status;

    // @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "donation")
    @OneToOne(mappedBy = "donationPayment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Invoice invoices;

}
