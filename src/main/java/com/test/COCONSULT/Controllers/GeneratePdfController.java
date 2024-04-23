package com.test.COCONSULT.Controllers;

import com.itextpdf.text.DocumentException;
import com.test.COCONSULT.Services.PDFGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/pdf")
public class GeneratePdfController {

    private final PDFGenerationService pdfGenerationService;

    @Autowired
    public GeneratePdfController(PDFGenerationService pdfGenerationService) {
        this.pdfGenerationService = pdfGenerationService;
    }

    @GetMapping("/generatePdf")
    public ResponseEntity<byte[]> generatePdf() {
        try {
            byte[] pdfData = pdfGenerationService.generatePDF();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "generated_pdf.pdf");

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
