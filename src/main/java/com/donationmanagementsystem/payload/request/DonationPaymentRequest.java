package com.donationmanagementsystem.payload.request;

import com.donationmanagementsystem.entity.Donation;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.utils.DonationStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DonationPaymentRequest {

    private String transactionId;

    @NotBlank(message = "Payment Method cannot be blank")
    @NotNull(message = "Payment Method cannot be blank")
    private String paymentMethod;

    private String currency;

    private LocalDate donatedAt;

    @NotNull(message = "Donation cannot be blank")
    private Long donationId;

    @NotNull(message = "Doner cannot be blank")
    private Long donerId;

    @NotNull(message = "Status cannot be blank")
    private DonationStatus status;

    @NotNull(message = "Amount donated be blank")
    private Long amountDonated;
}
