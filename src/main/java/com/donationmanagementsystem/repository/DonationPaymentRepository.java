package com.donationmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.entity.User;


public interface DonationPaymentRepository extends JpaRepository<DonationPayment, Long> {

    Optional<DonationPayment> findByCheckoutToken(String checkoutToken);


    List<DonationPayment> findByDonerOrderByIdDesc(User user);


    List<DonationPayment> findTop5ByDonerOrderByIdDesc(User user);


    @Query(value="SELECT SUM(dp.amount_donated) from donation_payments dp where dp.donated_by = :id AND dp.status = 'COMPLETED'", nativeQuery = true)
    Long calculateTotalByUser(@Param("id") Long id);

}
