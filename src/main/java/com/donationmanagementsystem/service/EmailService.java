package com.donationmanagementsystem.service;

import org.thymeleaf.context.Context;

import com.donationmanagementsystem.utils.EmailDetails;

public interface EmailService {

    Boolean sendMail(EmailDetails details);

    Boolean sendMailWithHtmlTemplate(EmailDetails details, String templateName, Context context);

    Boolean sendMailWithAttachment(EmailDetails details, String templateName);
}
