package com.donationmanagementsystem.controller.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.donationmanagementsystem.service.UserVerificationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserVerificationController {

    private final UserVerificationService userVerificationService;

    @Value("${dms.frontend.url}")
    private String browserUrl;

    @GetMapping("/auth/verify/{code}")
    public String verifyUser(@PathVariable("code") String code, Model model) {
        if (userVerificationService.verifyUser(code) == true) {
            return "redirect:/success";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("browser_url", browserUrl);
        return "user/success";
    }

    @GetMapping("/error")
    public String error() {
        return "user/error";
    }

}
