package com.donationmanagementsystem.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentIntentResponse {

    private boolean status;

    private String message;

    private String secret;

    private String checkoutToken;

}
