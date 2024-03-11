package com.donationmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.service.StorageService;

import lombok.Data;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value="/api/test")
public class TestController {

    @Data
    class FileUpload {

        public MultipartFile file;
    }

    @Autowired
    StorageService storageService;

    @PostMapping(value = "/upload-file",  consumes = { "multipart/form-data" })
    public ResponseEntity<ApiResponse> uploadFile(@ModelAttribute FileUpload file) {
        storageService.uploadFile(file.getFile(), "test");
        return new ResponseEntity<>(new ApiResponse(true, "File uploaded successfully"), HttpStatus.OK);
    }


    @GetMapping(value="/say-hello")
    public String hello(){
        return "./../../../../resources/invoice.html";
    }

}
