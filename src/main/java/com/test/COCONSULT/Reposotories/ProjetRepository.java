package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Integer> {

    Projet findProjetByIdProjet(Integer idProjet);

    Projet getProjetByProjectTitle(String projectTitle);
}
