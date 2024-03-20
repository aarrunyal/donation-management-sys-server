package com.donationmanagementsystem.service;

import org.thymeleaf.context.Context;

public interface PdfGeneratorService {
    

    public boolean generatePdf(Context context, String fileName, String templatePath);
}
