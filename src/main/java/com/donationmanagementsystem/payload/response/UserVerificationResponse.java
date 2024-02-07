package com.donationmanagementsystem.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

public class UserVerificationResponse {
    private Long id;
    private String token;
    private boolean expired;
}
