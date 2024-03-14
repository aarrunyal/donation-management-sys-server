package com.donationmanagementsystem.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DonationStatus {

    COMPLETED,
    PENDING,
    DECLINED,
    EXPIRED,
    CANCELLED
}
