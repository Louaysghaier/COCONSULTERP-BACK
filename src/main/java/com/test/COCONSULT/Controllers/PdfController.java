package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Interfaces.FileStorageService;

import com.test.COCONSULT.Reposotories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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

    @PostMapping(value = "/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            // Store the file and get the file name
            String fileName = fileStorageService.storeFile(file);
            //Contract contract = new Contract();
            // Set email provided by the user
            //contract.setDescription(fileName);
            // Set other properties of Candidat as needed

            //contractRepository.save(contract);
            return "File uploaded successfully: " + fileName;
        } catch (Exception e) {
            return "Failed to upload file: " + e.getMessage();
        }

    }
}
