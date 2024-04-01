package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Reclamation;
import com.test.COCONSULT.ServiceIMP.ReclamationServiceImp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Slf4j
@AllArgsConstructor
public class ReclamationController {
    @Autowired
    ReclamationServiceImp reclamationServiceImp;
    @Transactional

    @PostMapping("/{emailCandidat}")
    public ResponseEntity<Reclamation> ajouterReclamation(@RequestBody String contenu, @PathVariable String emailCandidat) {
        Reclamation reclamation = reclamationServiceImp.ajouterReclamation(contenu, emailCandidat);
        return new ResponseEntity<>(reclamation, HttpStatus.CREATED);
    }
}