package com.donationmanagementsystem.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.donationmanagementsystem.service.EmailService;
import com.donationmanagementsystem.utils.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service

public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public Boolean sendMail(EmailDetails details) {
        // Try block to check for exceptions
        // try {
        System.out.println(sender);
        // Creating a simple mail message
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // Setting up necessary details
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getReceipient());
        mailMessage.setText(details.getMsgBody());
        mailMessage.setSubject(details.getSubject());

        // Sending the mail
        javaMailSender.send(mailMessage);
        System.out.println("Mail Sent Successfully...");
        return true;
        // }

        // Catch block to handle the exceptions
        // catch (Exception e) {
        // System.out.println("Error while sending mail");
        // return false;
        // }
    }

    @Override
    public Boolean sendMailWithAttachment(EmailDetails details, Context context) {

        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be sends
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getReceipient());
            mimeMessageHelper.setText(templateEngine.process(details.getTemplateName(), context), true);
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Mail Sent Successfully...");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            return true;
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            System.out.println("Error while sending mail");
            return false;
        }
    }

    @Override
    public Boolean sendMailWithHtmlTemplate(EmailDetails details, Context context) {
        // Try block to check for exceptions
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setTo(details.getReceipient());
            helper.setSubject(details.getSubject());
            String htmlContent = templateEngine.process(details.getTemplateName(), context);
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            // Handle exception
            return false;
        }
    }

}
