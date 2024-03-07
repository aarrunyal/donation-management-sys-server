package com.donationmanagementsystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.donationmanagementsystem.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

}
