package com.donationmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.donationmanagementsystem.repository.DonationPaymentRepository;
import com.donationmanagementsystem.service.InvoiceService;

public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    DonationPaymentRepository donationPaymentRepository;

    @Autowired
    
    @Override
    public void createInvoice(String checkoutToken) {
        
    }
    
}
