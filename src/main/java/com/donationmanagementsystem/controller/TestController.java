package com.donationmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping("/invoice")
    public String invoiceDesign() {
        return "redirect:invoice.html";
    }

    @GetMapping(value="/check")
    public String check(){
        return "Hey Hi, I am fine";
    }
}
