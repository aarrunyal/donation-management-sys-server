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

    private String paymentMethod;

    private LocalDate donatedAt;

    @NotBlank(message = "Donation cannot be blank")
    @NotNull(message = "Donation cannot be blank")
    private Long donationId;

    @NotBlank(message = "Doner cannot be blank")
    @NotNull(message = "Doner cannot be blank")
    private Long donerId;

    @NotBlank(message = "Status cannot be blank")
    @NotNull(message = "Status cannot be blank")
    private DonationStatus status;

    @NotBlank(message = "Amount donated be blank")
    @NotNull(message = "Amount donated be blank")
    private Long amountDonated;
}
