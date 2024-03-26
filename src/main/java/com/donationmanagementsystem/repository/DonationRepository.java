package com.donationmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.donationmanagementsystem.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("""
                SELECT d FROM Donation d
                    WHERE
                    d.id != :id and
                     d.status = true and
                     d.expired = false and
                     d.verified = true
                     ORDER BY RAND() LIMIT :size
            """)
    List<Donation> findOtherCampaignRandomly(@Param("id") Long id, @Param("size") int size);

    // List<Donation> findAllByFilter(DonationFilterRequest donationFilterRequest);/

    @Modifying
    @Query("""
                UPDATE Donation d
            SET d.totalCollected = (SELECT SUM(p.amountDonated) FROM DonationPayment p
            WHERE d.id = p.donation.id AND p.status = 'COMPLETED')
            WHERE d.id = :donationId
            """)
    void calculateTotal(@Param("donationId") Long donationId);

}
