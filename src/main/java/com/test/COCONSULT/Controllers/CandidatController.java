package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.DTO.CandidatDetailsDTO;
import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.JobOpport;
import com.test.COCONSULT.Interfaces.CandidatServiceInterface;
import com.test.COCONSULT.ServiceIMP.CandidatServiceImp;
import com.test.COCONSULT.ServiceIMP.CandidateNotificationService;
import com.test.COCONSULT.Services.MailSenderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.test.COCONSULT.Entity.test;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/candidat")
public class CandidatController {
    CandidatServiceInterface candidatServiceInterface;
    private final CandidateNotificationService candidateNotificationService;
    @Autowired
    CandidatServiceImp candidatServiceImp;
    MailSenderService mailSenderService;


    @PostMapping("/createcandidat")
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat candidat) {
        Candidat createdCandidat = candidatServiceInterface.createCandidat(candidat);
        return new ResponseEntity<>(createdCandidat, HttpStatus.CREATED);
    }

    @PutMapping("/updatecandidat/{id}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable("id") int id, @RequestBody Candidat candidat) {
        Candidat updatedCandidat = candidatServiceInterface.updateCandidat(id, candidat);
        if (updatedCandidat != null) {
            return new ResponseEntity<>(updatedCandidat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletecandidat/{id}")
    public ResponseEntity<Void> deleteCandidat(@PathVariable("id") int id) {
        candidatServiceInterface.deleteCandidat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getallcandidat")
    public ResponseEntity<List<Candidat>> getAllCandidats() {
        List<Candidat> candidats = candidatServiceInterface.getAllCandidats();
        return new ResponseEntity<>(candidats, HttpStatus.OK);
    }

    @GetMapping("/getcandidatbyid/{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable("id") int id) {
        Candidat candidat = candidatServiceInterface.getCandidatById(id);
        if (candidat != null) {
            return new ResponseEntity<>(candidat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






    @PostMapping("/ajouter")
    public ResponseEntity<String> ajouterTest(@RequestParam int idQuestion, @RequestParam int idQuiz, @RequestParam int idCandidat, @RequestParam String selectedAnswer) {
        candidatServiceInterface.ajouterTest(idQuestion, idQuiz, idCandidat, selectedAnswer);
        return ResponseEntity.ok("Test ajouté avec succès.");
    }
    @PostMapping("/ ajouterCandidatAOffre/{idJobOpport}")
    public void ajouterCandidatAOffre(@RequestBody Candidat candidat, @PathVariable ("idJobOpport") int idJobOpport) {
        candidatServiceInterface.ajouterCandidatAOffre(candidat, idJobOpport);
    }

    @PostMapping("/add")
    public ResponseEntity<Candidat> addCandidat(@RequestParam String email) {
        // Appeler le service pour créer un candidat avec seulement l'e-mail
        Candidat newCandidat = candidatServiceImp.createCandidatWithOnlyEmail(email);
        return new ResponseEntity<>(newCandidat, HttpStatus.CREATED);
    }



    @GetMapping("/{email}/notify")
    public String notifyCandidate(@PathVariable("email") String candidateEmail) {
        Candidat candidat = candidatServiceInterface.getCandidatByEmail(candidateEmail);
        if (candidat == null) {
            return "Candidat with email " + candidateEmail + " not found";
        }

        double finalMark = getFinalMarkFromTests(candidat);

        // Envoi d'e-mail
        String subject = "Your Final Mark";
        String body = "Your final mark is: " + finalMark;

        try {
            mailSenderService.send(candidateEmail, subject, body);
            return "Notification sent to candidate with email " + candidateEmail;
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send notification to candidate with email " + candidateEmail;
        }
    }

    private double getFinalMarkFromTests(Candidat candidat) {
        double finalMark = 0;

        for (test test : candidat.getTest()) {
            finalMark += test.getFinalmark();
        }

        return finalMark;
    }

    @GetMapping("/candidat-details")
    public ResponseEntity<List<CandidatDetailsDTO>> getCandidatDetails() {
        List<Object[]> candidatDetails = candidatServiceImp.getCandidatDetails();
        List<CandidatDetailsDTO> candidatDetailsDTOList = new ArrayList<>();

        for (Object[] objects : candidatDetails) {
            Candidat candidat = (Candidat) objects[0];
            JobOpport jobOpport = (JobOpport) objects[1];
            double finalMark = (double) objects[2];

            CandidatDetailsDTO candidatDetailsDTO = new CandidatDetailsDTO();
            candidatDetailsDTO.setCandidatId(candidat.getId_condidat());
            candidatDetailsDTO.setCandidatEmail(candidat.getEmail());
            candidatDetailsDTO.setJobOpportTitle(jobOpport.getTitre());
            candidatDetailsDTO.setFinalMark(finalMark);

            candidatDetailsDTOList.add(candidatDetailsDTO);
        }

        return ResponseEntity.ok(candidatDetailsDTOList);
    }
}








