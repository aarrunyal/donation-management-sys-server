package com.donationmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.donationmanagementsystem.entity.Invoice;

import lombok.Value;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = """
            SELECT * from invoices where donation_payment_id = :donationPaymentId AND doner_id = :donerId
            """, nativeQuery = true)
    Invoice findByDonationPaymentIdDonerId(@Param("donationPaymentId") Long donationPaymentId,
            @Param("donerId") Long donerId);

    Invoice findTopByOrderByIdDesc();

    // findFirstByOrderByPublicationDateDesc
    Invoice findFirstByOrderByIdDesc();

    Invoice findByInvoiceNo(Long invoiceNo);

}
