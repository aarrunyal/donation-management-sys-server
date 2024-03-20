package com.donationmanagementsystem.controller.web;

import java.io.FileOutputStream;
import java.io.OutputStream;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import com.donationmanagementsystem.entity.Invoice;
import com.donationmanagementsystem.service.InvoiceService;
import com.donationmanagementsystem.service.PdfGeneratorService;

import io.jsonwebtoken.io.IOException;
import org.springframework.ui.Model;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    PdfGeneratorService generatorService;

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

    // @GetMapping(value = "/invoice/{invoiceNo}")
    // public String checkInvoice(@PathVariable("invoiceNo") Long invoiceNo, Model
    // model) {
    // Invoice invoice = invoiceService.findByInvoiceNo(invoiceNo);
    // model.addAttribute("invoice", invoice);
    // return "invoice/index";

    // }

    @GetMapping(value = "/invoice/{invoiceNo}")
    public String checkInvoice(@PathVariable("invoiceNo") Long invoiceNo, Model model)
            throws IOException, java.io.IOException {
        Invoice invoice = invoiceService.findByInvoiceNo(invoiceNo);
        Context context = new Context();
        context.setVariable("invoice", invoice);
        generatorService.generatePdf(context, "invoice-" + invoiceNo + ".pdf", "invoice/invoice.html");
        return "invoice/index";
    }

}
