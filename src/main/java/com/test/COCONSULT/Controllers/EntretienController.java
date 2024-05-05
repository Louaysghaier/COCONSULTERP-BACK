package com.test.COCONSULT.Controllers;


import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.Entretien;
import com.test.COCONSULT.Interfaces.CandidatServiceInterface;
import com.test.COCONSULT.ServiceIMP.CandidatServiceImp;
import com.test.COCONSULT.ServiceIMP.GoogleMeetAPIService;
import com.test.COCONSULT.Services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/entretiens")
public class EntretienController {

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    CandidatServiceInterface candidatServiceInterface;

    @Autowired
    GoogleMeetAPIService googleMeetAPIService;
    @PostMapping("/sendEntretienDetails")
    public String sendEntretienDetailsByEmail(@RequestParam String candidateEmail, @RequestParam int entretienId) {

            try {
                // Obtenez l'entretien en fonction de son ID en utilisant googleMeetAPIService
                Entretien entretien = googleMeetAPIService.getEntretienById(entretienId);


                // Vérifiez si l'entretien existe et que l'e-mail du candidat est fourni
            if (entretien != null && candidateEmail != null) {
                sendEntretienDetails(entretien, candidateEmail);
                return "Les détails de l'entretien ont été envoyés par e-mail avec succès.";
            } else {
                return "Impossible d'envoyer les détails de l'entretien par e-mail. Veuillez vérifier les informations fournies.";
            }
        } catch (MessagingException e) {
            return "Une erreur s'est produite lors de l'envoi des détails de l'entretien par e-mail : " + e.getMessage();
        }
    }
    public String sendEntretienDetails(Entretien entretien, String candidateEmail) throws MessagingException {
        Candidat candidat = candidatServiceInterface.getCandidatByEmail(candidateEmail);
        if (candidat == null) {
            return "Candidat with email " + candidateEmail + " not found";
        }


        String subject = "Détails de l'entretien";
        String body = "Voici les détails de l'entretien : <br>"
                + "Date: " + entretien.getDate() + "<br>"
                + "Heure: " + entretien.getHeure() + "<br>"


                + "Lien vers l'entretien : <a href=\"" + entretien.getLien() + "\">Lien vers l'entretien</a><br>";


        try {
            mailSenderService.send(candidateEmail, subject, body);
            return "Notification sent to candidate with email " + candidateEmail;
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send notification to candidate with email " + candidateEmail;
        }
    }
    @PostMapping("/add/{candidatId}")
    public ResponseEntity<Entretien> ajouterEntretienEtAssocierACandidat(@RequestBody Entretien entretien, @PathVariable int candidatId) {
        Entretien newEntretien = googleMeetAPIService.ajouterEntretienEtAssocierACandidat(entretien, candidatId);
        return new ResponseEntity<>(newEntretien, HttpStatus.CREATED);
    }
}


