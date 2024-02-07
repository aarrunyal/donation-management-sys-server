package com.donationmanagementsystem.payload.request;

import lombok.Data;

@Data

public class UserVerificationRequest {
    
    private Long id;
    private String token;
    private boolean expired;
}
