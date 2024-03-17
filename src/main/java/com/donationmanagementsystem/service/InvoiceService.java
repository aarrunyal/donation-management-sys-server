package com.donationmanagementsystem.service;

public interface InvoiceService {
    
    /**
     * @param checkoutToken
     */
    void createInvoice(String checkoutToken);
}
