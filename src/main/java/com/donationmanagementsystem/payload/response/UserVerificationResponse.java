package com.donationmanagementsystem.payload.response;

import lombok.Data;

@Data

public class UserVerificationResponse {
    private Long id;
    private String token;
    private boolean expired;
}
