package com.donationmanagementsystem.entity;

import java.time.LocalDate;
import java.util.List;

import com.donationmanagementsystem.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "donations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Donation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    private Long expectedCollection;

    @Column(nullable = false)
    private Long organisedFor;

    private boolean expired = false;

    private boolean status = true;

    private boolean verified = false;

    // @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organiser_id")
    private User organiser;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "donation")
    @JsonIgnore
    private List<Invoice> invoices;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "donation")
    @JsonIgnore
    private List<DonationPayment> payments;
}
