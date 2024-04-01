package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Expanses;
import com.test.COCONSULT.Interfaces.ExpansesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expanses")
@AllArgsConstructor
public class ExpansesController {

    private final ExpansesService expansesService;

    @GetMapping("GetAllExpanses")
    public ResponseEntity<List<Expanses>> retrieveExpanses() {
        List<Expanses> expansesList = expansesService.retrieveExpanses();
        return ResponseEntity.ok(expansesList);
    }

    @GetMapping("/GetExpanse/{id}")
    public ResponseEntity<Expanses> retrieveExpanses(@PathVariable("id") Long idExpanses) {
        Expanses expanses = expansesService.retrieveExpanses(idExpanses);
        if (expanses != null) {
            return ResponseEntity.ok(expanses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterExpanse")
    public ResponseEntity<Expanses> addExpanses(@RequestBody Expanses expanses) {
        Expanses savedExpanses = expansesService.addExpanses(expanses);
        return new ResponseEntity<>(savedExpanses, HttpStatus.CREATED);
    }

    @PutMapping("/updateExpanse/{id}")
    public ResponseEntity<Expanses> updateExpanses(@PathVariable("id") Long id, @RequestBody Expanses expanses) {
        expanses.setIdExps(id); // Set the ID from the path variable
        Expanses updatedExpanses = expansesService.updateExpanses(expanses);
        if (updatedExpanses != null) {
            return ResponseEntity.ok(updatedExpanses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteExpanse/{id}")
    public ResponseEntity<Void> removeExpanses(@PathVariable("id") Long idExpanses) {
        expansesService.removeExpanses(idExpanses);
        return ResponseEntity.noContent().build();
    }
}
