package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.JobOpport;
import com.test.COCONSULT.Interfaces.CandidatServiceInterface;
import com.test.COCONSULT.Interfaces.FileStorageService;
import com.test.COCONSULT.Reposotories.CandidatRepository;
import com.test.COCONSULT.Reposotories.JobOpportRepository;
import com.test.COCONSULT.ServiceIMP.CandidateNotificationService;
import com.test.COCONSULT.Services.MailSenderService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;


import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class PdfController {
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    CandidatRepository candidatRepository;
    @Autowired
    CandidatServiceInterface candidatServiceInterface;
    @Autowired
    private JobOpportRepository jobOpportRepository;
    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    public PdfController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;

    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            // Store the file and get the file name
            String fileName = fileStorageService.storeFile(file);

            return "File uploaded successfully: " + fileName;
        } catch (Exception e) {
            return "Failed to upload file: " + e.getMessage();
        }

    }





    @PostMapping( "/uploadAndExtract")
    public String uploadAndExtract(@RequestParam("jobOpportId") int jobOpportId,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("email") String email) {
        try {
            // Vérifier si l'email du candidat existe déjà dans la base de données
            if (candidatRepository.existsByEmail(email)) {
                return "Cet e-mail existe déjà dans notre base de données.";
            }

            String fileName = fileStorageService.storeFile(file);
            String message = "";
            // Extract text from PDF
            String extractedText = extractTextFromPDF(file.getInputStream());

            // Retrieve JobOpport from database (assuming jobOpportId is known)
            JobOpport jobOpport = jobOpportRepository.findById(jobOpportId);

            // Check if extracted text contains mots1 and mots2 from JobOpport
            String descriptionLowerCase = jobOpport.getDescription().toLowerCase();
            String titreLowerCase = jobOpport.getTitre().toLowerCase();
            if (extractedText.contains(descriptionLowerCase) && extractedText.contains(titreLowerCase)) {
                // Create and save Candidat entity with user-provided email
                Candidat candidat = new Candidat();
                candidat.setEmail(email); // Set email provided by the user
                candidat.setPdfFile(fileName); // Set stored file name
                // Set other properties of Candidat as needed

                // Assign the job opportunity to the candidate
                candidat.setJobOpport(jobOpport);

                candidatRepository.save(candidat);
                String siteUrl = "http://localhost:4200/#/email";
                message = "Félicitations ! Votre CV correspond à notre offre d'emploi. Visitez notre site pour passer le coding game : <a href=\"" + siteUrl + "\">" + siteUrl + "</a>";

            } else {
                message = "Désolé, votre CV ne correspond pas à notre offre d'emploi.";
            }

            // Envoyer un e-mail au candidat
            try {
                mailSenderService.send(email, "Résultat de votre candidature", message);
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Échec de l'envoi de l'e-mail au candidat.";
            }

            return message;
        } catch (IOException e) {
            e.printStackTrace();
            return "Échec de l'extraction du texte du PDF ou de l'upload du fichier.";
        }

    }


    private String extractTextFromPDF(InputStream inputStream) throws IOException {
        PDDocument document = PDDocument.load(inputStream);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();

        // Convertir le texte en minuscules (ou en majuscules) pour ignorer la casse
        text = text.toLowerCase(); // Ou text.toUpperCase() si vous le souhaitez

        return text;
    }


    @GetMapping("/testExtractText")
    public String testExtractText() {
        try {
            // Charger le fichier PDF pour les tests depuis le système de fichiers
            File testPdfFile = new File("C:\\xampp1/dorra.pdf");

            // Extraire le texte du fichier PDF
            String extractedText = extractTextFromPDF(new FileInputStream(testPdfFile));

            return extractedText;
        } catch (IOException e) {
            return "Erreur lors de l'extraction du texte du PDF : " + e.getMessage();
        }
    }


}
