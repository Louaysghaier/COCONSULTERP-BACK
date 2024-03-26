package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Interfaces.FileStorageService;

import com.test.COCONSULT.Reposotories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class PdfController {
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    ContractRepository contractRepository ;

    @Autowired
    public PdfController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/pdfs/{pdfName:.+}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String pdfName) throws IOException {
        // Construct the full path to the PDF file
        Path pdfPath = Paths.get(uploadDir, pdfName);

        // Read the PDF bytes from the file
        byte[] pdfBytes = Files.readAllBytes(pdfPath);

        // Determine the MIME type of the PDF based on its file extension
        String contentType = Files.probeContentType(pdfPath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));

        // Return the PDF bytes along with appropriate headers
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }

        try {
            String fileName = fileStorageService.storeFile(file);

            // Enregistrer le fichier dans la base de données avec son nom
            Contract contract = new Contract();   // lezim naamel haka bch yetsajjel bessmou fil base de données
            contract.setDescription(fileName);
            //contractRepository.save(contract);

            return ResponseEntity.ok().body("File uploaded successfully: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }

}