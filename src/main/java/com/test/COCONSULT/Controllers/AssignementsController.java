package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Interfaces.AssignementsService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("assignements")
@RestController
@AllArgsConstructor
public class AssignementsController{

    private final AssignementsService assignementsService;

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
}