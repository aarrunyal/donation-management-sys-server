package com.donationmanagementsystem.payload.response;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.donationmanagementsystem.entity.Donation;
import com.donationmanagementsystem.entity.Invoice;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.utils.DonationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationPaymentResponse {

    private Long id;

    private String checkoutToken;

    private String transactionId;

    private String paymentMethod;

    private Long amountDonated;

    private LocalDate donatedAt;

    private Long organisedFor;

    @JsonIgnore
    private Donation donation;

    @JsonIgnore
    private User doner;

    @JsonIgnore
    private Invoice invoice;

    private DonationStatus status;

    private String currency;

    public String getDonationName() {
        return this.donation.getName();
    }

    public String getDonerName() {
        return this.doner.getFirstName() + " " + this.doner.getLastName();
    }

    public Long getInvoiceNo() {
        if (this.invoice != null)
            return this.invoice.getInvoiceNo();
        return null;
    }

}
