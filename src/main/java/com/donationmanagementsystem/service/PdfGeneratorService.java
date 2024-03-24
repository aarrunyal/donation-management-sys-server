package com.donationmanagementsystem.service;

import java.io.FileNotFoundException;

import org.thymeleaf.context.Context;

public interface PdfGeneratorService {
    

    public String generatePdf(Context context, String fileName, String templatePath) throws FileNotFoundException;
}
