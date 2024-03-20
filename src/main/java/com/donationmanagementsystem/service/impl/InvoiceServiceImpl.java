package com.donationmanagementsystem.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.entity.Invoice;
import com.donationmanagementsystem.repository.InvoiceRepository;
import com.donationmanagementsystem.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Value("${starting.invoice.no}")
    private Long invoiceNo;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public Invoice  createInvoice(DonationPayment donationPayment) {

        Invoice invoice = invoiceRepository.findByDonationPaymentIdDonerId(
                donationPayment.getId(), donationPayment.getDoner().getId());

        if (invoice == null) {
            var newInvoice = this.buildInvoiceData(donationPayment);
            if (newInvoice != null) {
                return invoiceRepository.save(newInvoice);
            }
        }
        return null;
    }

    @Override
    public Long generateInvoiceNo() {
        var number = invoiceNo;
        Invoice lastInvoice = invoiceRepository.findFirstByOrderByIdDesc();
        if (lastInvoice == null) {
            return number;
        } else {
            number = lastInvoice.getInvoiceNo();
            return number + 1;
        }
    }

    @Override
    public Invoice buildInvoiceData(DonationPayment donationPayment) {
        try {
            // PaymentMethod paymentMethod = paymentService
            // .getPaymentDetail(donationPaymentResponse.getTransactionId());
            return Invoice.builder()
                    .subTotal(donationPayment.getAmountDonated())
                    .total(donationPayment.getAmountDonated())
                    .invoiceNo(generateInvoiceNo())
                    .doner(donationPayment.getDoner())
                    .donationPayment(donationPayment)
                    .lastName(donationPayment.getDoner().getLastName())
                    .firstName(donationPayment.getDoner().getFirstName())
                    .email(donationPayment.getDoner().getEmail())
                    .donationAmount(donationPayment.getAmountDonated())
                    .phoneNumber("905")
                    .invoiceDate(LocalDate.now())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Invoice findByInvoiceNo(Long invoiceNo) {
        return invoiceRepository.findByInvoiceNo(invoiceNo);
    }
}