package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Interfaces.ProjetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projets")
public class ProjetsController {

    private final ProjetsService projetsService;

    @Autowired
    public ProjetsController(ProjetsService projetsService) {
        this.projetsService = projetsService;
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

    @PostMapping("/ajouterproject")
    public ResponseEntity<Projets> addProjets(@RequestBody Projets projets) {
        Projets savedProjets = projetsService.addProjets(projets);
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
}
