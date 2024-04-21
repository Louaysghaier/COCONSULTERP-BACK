package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Interfaces.AssignementsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("assignements")
@RestController
@AllArgsConstructor
public class AssignementsController{

    private AssignementsService assignementsService;

    @GetMapping("/getAllAssigns")
    public ResponseEntity<List<Assignements>> retrieveAssignements() {
        List<Assignements> assignementsList = assignementsService.retrieveAssignements();
        return ResponseEntity.ok(assignementsList);
    }

    @GetMapping("/getAssign/{id}")
    public ResponseEntity<Assignements> retrieveAssignements(@PathVariable("id") Long idAssignements) {
        Assignements assignements = assignementsService.retrieveAssignements(idAssignements);
        if (assignements != null) {
            return ResponseEntity.ok(assignements);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/ajouterAssign")
    public Assignements addAssignements(@RequestBody Assignements assignements) {
        return assignementsService.addAssignements(assignements);

    }
    @PutMapping("/updateAssig/{id}")
    public ResponseEntity<Assignements> updateAssignements(@PathVariable("id") Long id, @RequestBody Assignements assignements) {
        assignements.setIdAssign(id); // Set the ID from the path variable
        Assignements updatedAssignements = assignementsService.updateAssignements(assignements);
        if (updatedAssignements != null) {
            return ResponseEntity.ok(updatedAssignements);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/RemoveAssign/{id}")
    public ResponseEntity<Void> removeAssignements(@PathVariable("id") Long idAssignements) {
        assignementsService.removeAssignements(idAssignements);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{idProjet}/getAssignmentsForProject")
    public ResponseEntity<List<Assignements>> getAssignmentsForProject(@PathVariable("idProjet") Long idProjet) {
        List<Assignements> assignments = assignementsService.getAssignmentsForProject(idProjet);
        return ResponseEntity.ok(assignments);

    }
    @GetMapping("/{idProjet}/getAssignmentsUpdatedAfterDate")
    public ResponseEntity<List<Assignements>> getAssignmentsUpdatedAfterDate(
            @PathVariable("idProjet") Long idProjet,
            @RequestParam("date") String date) {
        List<Assignements> assignments = assignementsService.getAssignmentsUpdatedAfterDate(idProjet, LocalDate.parse(date));
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{idProjet}/getLastAssignments")
    public ResponseEntity<List<Assignements>> getLastAssignments(
            @PathVariable("idProjet") Long idProjet) {
        List<Assignements> assignments = assignementsService.getLastAssignments(idProjet);
        return ResponseEntity.ok(assignments);
    }
}