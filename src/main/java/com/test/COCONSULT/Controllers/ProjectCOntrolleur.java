package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Projet;
import com.test.COCONSULT.Interfaces.ProjectInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Projet")
@CrossOrigin("*")
public class ProjectCOntrolleur {
    @Autowired
    ProjectInterface projectInterface;
    @PostMapping("/addProjet")
    public Projet addProjet(@RequestBody Projet projet) {
        return projectInterface.addProjet(projet);
    }
    @GetMapping("/getAllProjets")
    public List<Projet> getAllProjets() {
        return projectInterface.getAllProjets();
    }
    @GetMapping("/getProjetbyTitle/{projectTitle}")
    public Projet getProjetbyTitle(@PathVariable("projectTitle") String projectTitle) {
        return projectInterface.getProjetbyTitle(projectTitle);
    }
}
