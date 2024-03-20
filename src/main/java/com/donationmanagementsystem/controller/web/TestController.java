package com.donationmanagementsystem.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.donationmanagementsystem.entity.Invoice;
import com.donationmanagementsystem.service.InvoiceService;

import org.springframework.ui.Model;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    InvoiceService invoiceService;

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

    @GetMapping(value = "/invoice/{invoiceNo}")
    public String checkInvoice(@PathVariable("invoiceNo") Long invoiceNo, Model model) {
        Invoice invoice = invoiceService.findByInvoiceNo(invoiceNo);
        model.addAttribute("invoice", invoice);
        return "invoice/index";

    }

}
