package com.donationmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.entity.User;

public interface DonationPaymentRepository extends JpaRepository<DonationPayment, Long> {

    Optional<DonationPayment> findByCheckoutToken(String checkoutToken);

}
