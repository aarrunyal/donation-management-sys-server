package com.donationmanagementsystem.service;

import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.entity.Invoice;


public interface InvoiceService {

    /**
     * @param checkoutToken
     */
    void createInvoice(DonationPayment donationPayment);

    Long generateInvoiceNo();

    Invoice buildInvoiceData(DonationPayment donationPayment);
}
