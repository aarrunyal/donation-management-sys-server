package com.donationmanagementsystem.payload.request;


import com.donationmanagementsystem.utils.DonationStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DonationPaymentUpdateRequest {

    @NotNull(message = "Status cannot be blank")
    private DonationStatus status;

    @NotNull(message = "Transaction donated be blank")
    private String transactionId;
}
