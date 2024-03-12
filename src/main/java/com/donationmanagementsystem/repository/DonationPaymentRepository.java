package com.donationmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donationmanagementsystem.entity.DonationPayment;

public interface DonationPaymentRepository extends JpaRepository<DonationPayment, Long> {

}
