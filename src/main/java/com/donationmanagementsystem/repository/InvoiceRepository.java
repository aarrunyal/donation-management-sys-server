package com.donationmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donationmanagementsystem.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
