package com.donationmanagementsystem.service;

import com.donationmanagementsystem.utils.EmailDetails;

public interface EmailService {
    
    Boolean sendMail(EmailDetails details);

    Boolean sendMailWithAttachment(EmailDetails details);
}
