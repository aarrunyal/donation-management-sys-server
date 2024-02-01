package com.donationmanagementsystem.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/organiser")
public class OrganiserController {

    @GetMapping
    public String get() {
        return "GET:: organiser controller";
    }
    @PostMapping
    public String post() {
        return "POST:: organiser controller";
    }
    @PutMapping
    public String put() {
        return "PUT:: organiser controller";
    }
    @DeleteMapping
    public String delete() {
        return "DELETE:: organiser controller";
    }
}