package com.donationmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.service.EmailService;
import com.donationmanagementsystem.service.StorageService;
import com.donationmanagementsystem.utils.EmailDetails;

import lombok.Data;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/test")
public class ApiTestController {

    @Data
    class FileUpload {

        public MultipartFile file;
    }

    @Autowired
    StorageService storageService;

    @Autowired
    EmailService emailService;

    @GetMapping(value = "/check")
    public String check() {
        return "Hey Hi, I am fine";
    }

    @PostMapping(value = "/upload-file", consumes = { "multipart/form-data" })
    public ResponseEntity<ApiResponse> uploadFile(@ModelAttribute FileUpload file) {
        storageService.uploadFile(file.getFile(), "test");
        return new ResponseEntity<>(new ApiResponse(true, "File uploaded successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/say-hello")
    public String hello() {
        return "./../../../../resources/invoice.html";
    }

    @GetMapping("/mail")
    public ResponseEntity<Boolean> testMail() {
        return new ResponseEntity<>(true, HttpStatus.OK);
        // var emailDetails = EmailDetails.builder().msgBody("Hello this is test mail")
        //         .receipient("aarrunyal@gmail.com")
        //         .subject("Test mail")
        //         .build();
        // return new ResponseEntity<>(emailService.sendMail(emailDetails), HttpStatus.OK);
    }
}
