package com.donationmanagementsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.donationmanagementsystem.entity.Donation;
import com.donationmanagementsystem.entity.DonationPayment;
import com.donationmanagementsystem.entity.Invoice;
import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.payload.request.DonationPaymentRequest;
import com.donationmanagementsystem.payload.request.DonationPaymentUpdateRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.DonationPaymentResponse;
import com.donationmanagementsystem.payload.response.PaymentIntentResponse;
import com.donationmanagementsystem.repository.DonationPaymentRepository;
import com.donationmanagementsystem.repository.DonationRepository;
import com.donationmanagementsystem.repository.UserRepository;
import com.donationmanagementsystem.service.DonationPaymentService;
import com.donationmanagementsystem.service.EmailService;
import com.donationmanagementsystem.service.InvoiceService;
import com.donationmanagementsystem.service.StorageService;
import com.donationmanagementsystem.utils.DonationStatus;
import com.donationmanagementsystem.utils.EmailDetails;
import com.donationmanagementsystem.utils.Helper;
import com.donationmanagementsystem.utils.ResponseMessage;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonationPaymentServiceImpl implements DonationPaymentService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    private final DonationPaymentRepository donationPaymentRepository;

    private final DonationRepository donationRepository;

    private final UserRepository userRepository;

    private Stripe stripe;

    private final PdfGeneratorServiceImpl pdfGeneratorServiceImpl;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    StorageService storageService;

    @Override
    public DonationPaymentResponse create(DonationPaymentRequest donationPaymentRequest) {
        try {
            DonationPayment donationPayment = new DonationPayment();
            if (donationPaymentRequest.getDonerId() != null) {
                User user = userRepository.findById(donationPaymentRequest.getDonerId()).orElseThrow(() -> {
                    throw new ResourceNotFoundException("User", "id", donationPaymentRequest.getDonerId());
                });
                donationPayment.setDoner(user);
            }

            if (donationPaymentRequest.getDonationId() != null) {
                Donation donation = donationRepository.findById(donationPaymentRequest.getDonationId())
                        .orElseThrow(() -> {
                            throw new ResourceNotFoundException("Donation", "id", donationPaymentRequest.getDonerId());
                        });
                donationPayment.setDonation(donation);
            }
            donationPayment.setPaymentMethod(donationPaymentRequest.getPaymentMethod());
            donationPayment.setAmountDonated(donationPaymentRequest.getAmountDonated());
            var checkoutToken = Helper.getTimestamp() + Helper.getRandomToken(10);
            donationPayment.setCheckoutToken(checkoutToken);
            donationPayment.setStatus(DonationStatus.PENDING);
            donationPayment.setCurrency(donationPaymentRequest.getCurrency());

            DonationPayment newDonationPayment = donationPaymentRepository.save(donationPayment);
            DonationPaymentResponse donationResponse = this.modelMapper.map(newDonationPayment,
                    DonationPaymentResponse.class);
            return donationResponse;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ResponseEntity<ApiResponse> update(DonationPaymentUpdateRequest donationPaymentRequest,
            String checkoutToken) {
        try {
            DonationPayment savedDonationPayment = donationPaymentRepository.findByCheckoutToken(checkoutToken)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Donation Payment", "checkout token", checkoutToken));

            savedDonationPayment.setTransactionId(donationPaymentRequest.getTransactionId());
            if (donationPaymentRequest.getTransactionId() != null
                    && donationPaymentRequest.getStatus() != DonationStatus.COMPLETED)
                return ResponseMessage.internalServerError("Invalid data supplied !!!");
            savedDonationPayment.setDonatedAt(LocalDate.now());
            savedDonationPayment.setStatus(donationPaymentRequest.getStatus());
            DonationPayment donationPayment = donationPaymentRepository.save(savedDonationPayment);
            if (donationPayment != null) {
                this.createInvoiceAndSendEmail(donationPayment);
            }
            return ResponseMessage.ok("Donation payment has been updated successfully !!!");
        } catch (Exception ex) {
            return ResponseMessage.internalServerError(null);
        }
    }

    public boolean createInvoiceAndSendEmail(DonationPayment donationPayment) throws FileNotFoundException {
        try {
            Invoice invoice = invoiceService.createInvoice(donationPayment);
            if (invoice != null) {
                Context context = new Context();
                context.setVariable("invoice", invoice);
                var invoicePath = pdfGeneratorServiceImpl.generatePdf(context,
                        "invoice-" + invoice.getInvoiceNo() + ".pdf",
                        "invoice/index.html");
                System.out.println(invoicePath);
                var emailDetails = EmailDetails.builder()
                        .receipient(donationPayment.getDoner().getEmail())
                        .subject("Donation Payment")
                        .msgBody("Donation payment has been made")
                        .templateName("email/payment-made")
                        .attachment(invoicePath).build();
                if (emailService.sendMailWithAttachment(emailDetails, context) == true) {
                    storageService.deleteFile(invoicePath);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("Email has been sent to doner and pdf has been deleted successfully !!!");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }

                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Error sending email");
            return false;

        }
    }

    @Override
    public ResponseEntity<DonationPaymentResponse> show(Long donationPaymentId) {
        DonationPayment savedDonationPayment = donationPaymentRepository.findById(donationPaymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Donation Payment", "id", donationPaymentId));
        return new ResponseEntity<>(this.modelMapper.map(savedDonationPayment, DonationPaymentResponse.class),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DonationPaymentResponse>> all() {
        List<DonationPayment> donationPayments = donationPaymentRepository.findAll(Helper.sortByAsc("id", "DESC"));
        List<DonationPaymentResponse> donationPaymentResponses = donationPayments
                .stream()
                .map(
                        (payment) -> this.modelMapper
                                .map(payment, DonationPaymentResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<List<DonationPaymentResponse>>(donationPaymentResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long donationPaymentId) {
        try {
            DonationPayment savedDonationPayment = donationPaymentRepository.findById(donationPaymentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Donation Payment", "id", donationPaymentId));
            if (savedDonationPayment != null) {
                donationPaymentRepository.delete(savedDonationPayment);
            }
            return ResponseMessage.ok("Donation deleted successfully");
        } catch (Exception ex) {
            return ResponseMessage.internalServerError("");
        }
    }

    @Override
    public ResponseEntity<PaymentIntentResponse> createPaymentIntent(DonationPaymentResponse donationPaymentResponse) {
        Stripe.apiKey = secretKey;
        try {
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setAmount(donationPaymentResponse.getAmountDonated() * 100)
                    .setCurrency(donationPaymentResponse.getCurrency())
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setAllowRedirects(AllowRedirects.NEVER)
                                    .setEnabled(true)
                                    .build())
                    .build();
            PaymentIntent paymentIntent = PaymentIntent.create(createParams);
            return new ResponseEntity<>(
                    new PaymentIntentResponse(true, "Payment intent created successfully",
                            paymentIntent.getClientSecret(), donationPaymentResponse.getCheckoutToken()),
                    HttpStatus.OK);
        } catch (StripeException e) {
            return new ResponseEntity<>(new PaymentIntentResponse(false, e.getMessage(), null, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
