package com.donationmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    public PaymentMethod getPaymentDetail(String paymentId) {
        try {
            Stripe.apiKey = secretKey;
            return PaymentMethod.retrieve(paymentId);
        } catch (StripeException e) {
            return null;
        }

    }
}
