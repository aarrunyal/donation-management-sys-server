package com.donationmanagementsystem.payload.response;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.utils.AppConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationResponse {

    private Long id;

    private String name;

    private String description;

    private LocalDate eventDate;

    private Long expectedCollection;

    private Long organisedFor;

    private boolean expired;

    private boolean status;

    private boolean verified;

    @JsonIgnore
    private User organiser;

    private String image;

    @JsonIgnore
    List<DonationPayment> payments;

    public String getImagePath() {
        return AppConstant.UPLOAD_ROOT_PATH + "/donation";
    }

    // public Long getPaymentTotal() {
    //     long total = (long) 0;
    //     if (this.payments.isEmpty())
    //         return total;

    //     // payment.getAmountDonated() : (long) 0);
    //     for (var payment : this.payments) {
    //         System.out.println(payment);
    //         total += (long) (payment.getAmountDonated() != null ? payment.getAmountDonated() : 0);
    //     }
    //     return total;
    // }

}
