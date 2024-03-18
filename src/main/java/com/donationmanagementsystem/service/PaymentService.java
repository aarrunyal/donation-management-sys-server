package com.donationmanagementsystem.service;

import com.stripe.model.PaymentMethod;

public interface PaymentService {
    public PaymentMethod getPaymentDetail(String paymentId);
}
