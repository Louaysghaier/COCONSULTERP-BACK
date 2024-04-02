package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Projet;
import com.test.COCONSULT.Reposotories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectInterface implements com.test.COCONSULT.Interfaces.ProjectInterface {
    @Autowired
    ProjetRepository projetRepository;
    @Override
    public Projet addProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    @Override
    public Projet getProjetbyTitle(String projectTitle) {
        return projetRepository.getProjetByProjectTitle(projectTitle);
    }
}
