package com.donationmanagementsystem.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
    List<Donation> findOtherCampaignRandomly(@Param("id") Long id,@Param("size") int size);

}
