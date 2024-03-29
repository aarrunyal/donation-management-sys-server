package com.donationmanagementsystem.service;

import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.entity.Invoice;

public interface InvoiceService {

    /**
     * @param checkoutToken
     */
    Invoice createInvoice(DonationPayment donationPayment);

    Long generateInvoiceNo();

    Invoice buildInvoiceData(DonationPayment donationPayment);

    Invoice findByInvoiceNo(Long invoiceNo);
}
