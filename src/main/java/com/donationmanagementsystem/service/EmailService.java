package com.donationmanagementsystem.service;

import org.thymeleaf.context.Context;

import com.donationmanagementsystem.utils.EmailDetails;

public interface EmailService {

    Boolean sendMail(EmailDetails details);

    Boolean sendMailWithHtmlTemplate(EmailDetails details, Context context);

    Boolean sendMailWithAttachment(EmailDetails details, Context context);
}
