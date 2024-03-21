package com.donationmanagementsystem.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.donationmanagementsystem.entity.Invoice;
import com.donationmanagementsystem.service.InvoiceService;


@Controller
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping(value = "/invoice/{invoiceNo}")
    public String checkInvoice(@PathVariable("invoiceNo") Long invoiceNo, Model model) {
        Invoice invoice = invoiceService.findByInvoiceNo(invoiceNo);
        model.addAttribute("invoice", invoice);
        return "invoice/index";
    }
}
