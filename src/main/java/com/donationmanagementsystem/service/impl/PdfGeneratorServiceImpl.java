package com.donationmanagementsystem.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.donationmanagementsystem.service.PdfGeneratorService;

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    @Override
    public String generatePdf(Context context, String fileName, String templatePath) throws FileNotFoundException {
        try {
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("templates/");
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode(TemplateMode.HTML);
            templateResolver.setCacheable(false);
            templateResolver.setCharacterEncoding("UTF-8");

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            // var html = templateEngine.process("invoice/invoice.html", context);
            var html = templateEngine.process(templatePath, context);

            var outputFolder = Paths.get(".").normalize().toAbsolutePath()
                    .toString() + "/public/pdf/";
            File dir = new File(outputFolder);
            if (dir.exists() == false) {
                dir.mkdirs();
            }
            var outputFile = outputFolder + fileName;
            OutputStream outputStream = new FileOutputStream(outputFile);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            outputStream.close();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Invoice generated successfully");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(outputFile);
            return outputFile;
        } catch (Exception exception) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(exception.getMessage());
            System.out.println("Error while generating new invoice pdf");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            return null;
        }

    }

}
