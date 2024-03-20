package com.donationmanagementsystem.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/email-template")
    public String emailTemplate(Model model) {
        model.addAttribute("message", "Hello from Thymeleaf!");
        model.addAttribute("name", "Arun");
        model.addAttribute("link", "Arun");
        return "email/new-account";
    }

    @GetMapping("/invoice")
    public String invoiceDesign() {
        return "redirect:invoice.html";
    }   

    @GetMapping(value = "/check")
    public String check() {
        return "Hey Hi, I am fine";
    
    }

    
}
