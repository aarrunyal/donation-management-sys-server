package com.donationmanagementsystem.service;

import com.donationmanagementsystem.utils.EmailDetails;

public interface EmailService {
    
    String sendMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
