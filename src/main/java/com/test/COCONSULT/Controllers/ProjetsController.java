package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Entity.Expanses;
import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Interfaces.AssignementsService;
import com.test.COCONSULT.Interfaces.ExpansesService;
import com.test.COCONSULT.Interfaces.MeetingsService;
import com.test.COCONSULT.Interfaces.ProjetsService;
import com.test.COCONSULT.Services.MailProject;
import com.test.COCONSULT.Services.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projets")
public class ProjetsController {


    @Autowired
    private SMSService smsService;

    @Autowired
    MailProject mailProject;
    @Autowired
    private ProjetsService projetsService;
    @Autowired
    private  ExpansesService expansesService;
    @Autowired
    private  AssignementsService assignementsService;
    @Autowired
    private  MeetingsService meetingsService;
    @Autowired
    public ProjetsController(ProjetsService projetsService) {
        this.projetsService = projetsService;
    }
    @PutMapping("/validateProject/{idProject}")
    public ResponseEntity<Void> validProject(@PathVariable("idProject") Long idProject) {
        Projets projet = projetsService.retrieveProjets(idProject);
        if (projet == null) {
            return ResponseEntity.notFound().build();
        }

        String consultantEmail = projet.getMail();

        // Préparer le contenu de l'e-mail
        String subject = "Début de travail sur le projet";
        String body = "<html><body>" +
                "<p>Cher Consultant,</p>" +
                "<p>Vous êtes désigné comme le consultant responsable du projet '" + projet.getProjetTitle() + "'.</p>" +
                "<p>Le travail sur ce projet commencera le " + projet.getDateDebut() + "</p>" +
                "<p>Cordialement,<br>L'équipe COCONSULT</p>" +
                "</body></html>";
        try {
            mailProject.send(consultantEmail, subject, body);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Projets>> retrieveProjets() {
        List<Projets> projetsList = projetsService.retrieveProjets();
        return ResponseEntity.ok(projetsList);
    }

    @GetMapping("/getProjectById/{idProjets}")
    public ResponseEntity<Projets> retrieveProjets(@PathVariable("idProjets") Long idProjets) {
        Projets projets = projetsService.retrieveProjets(idProjets);
        if (projets != null) {
            return ResponseEntity.ok(projets);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/addproject")
    public ResponseEntity<Projets> addProjets(@RequestBody Projets projets) {
        Projets savedProjets = projetsService.addProjets(projets);
        String message = "Le projet " + projets.getProjetTitle() + " a été ajouté avec succès.";
        smsService.sendSms("+NUMERO_DESTINATAIRE", message); // Remplacez NUMERO_DESTINATAIRE par le numéro de téléphone du destinataire

        return new ResponseEntity<>(savedProjets, HttpStatus.CREATED);
    }

    @PutMapping("/updateProject/{idProjets}")
    public ResponseEntity<Projets> updateProjets(@PathVariable("idProjets") Long idProjets, @RequestBody Projets projets) {
        projets.setIdProjet(idProjets); // Set the ID from the path variable
        Projets updatedProjets = projetsService.updateProjets(projets);
        if (updatedProjets != null) {
            return ResponseEntity.ok(updatedProjets);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteProject/{idProjets}")
    public ResponseEntity<Void> removeProjets(@PathVariable("idProjets") Long idProjets) {
        projetsService.removeProjets(idProjets);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/expanses")
    public ResponseEntity<List<Expanses>> getExpansesForProject(@PathVariable("id") Long projectId) {
        List<Expanses> expanses = expansesService.getExpansesForProject(projectId);
        return ResponseEntity.ok(expanses);
    }

    @GetMapping("/{id}/assignements")
    public ResponseEntity<List<Assignements>> getAssignementsForProject(@PathVariable("id") Long projectId) {
        List<Assignements> assignements = assignementsService.getAssignmentsForProject(projectId);
        return ResponseEntity.ok(assignements);
    }

    @PostMapping("/{id}/assignements")
    public ResponseEntity<Assignements> createAssignementForProject(@PathVariable("id") Long projectId, @RequestBody Assignements assignements) {
        // Assurez-vous de définir le projet pour l'assignement
        assignements.setProjets(projetsService.retrieveProjets(projectId));
        Assignements savedAssignement = assignementsService.addAssignements(assignements);
        return new ResponseEntity<>(savedAssignement, HttpStatus.CREATED);
    }

    /*@GetMapping("/{id}/meetings")
    public ResponseEntity<List<Meetings>> getMeetingsForProject(@PathVariable("id") Long projectId) {
        List<Meetings> meetings = meetingsService.getMeetingsForProject(projectId);
        return ResponseEntity.ok(meetings);
    }*/
    @PostMapping("/{id}/meetings")
    public ResponseEntity<Meetings> createMeetingForProject(@PathVariable("id") Long projectId, @RequestBody Meetings meeting) {
        // Assurez-vous de définir le projet pour la réunion
        meeting.setProjets(projetsService.retrieveProjets(projectId));
        Meetings savedMeeting = meetingsService.ajouterMeet(meeting);
        return new ResponseEntity<>(savedMeeting, HttpStatus.CREATED);
    }

}
