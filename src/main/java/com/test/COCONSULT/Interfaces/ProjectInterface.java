package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Projet;

import java.util.List;

public interface ProjectInterface {

    Projet addProjet(Projet projet);

    public List<Projet> getAllProjets();


    Projet getProjetbyTitle(String projectTitle);

}
