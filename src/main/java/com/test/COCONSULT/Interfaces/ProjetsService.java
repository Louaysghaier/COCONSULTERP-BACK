package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Projets;

import java.util.List;

public interface ProjetsService {
    List<Projets> retrieveProjets();

    Projets updateProjets(Projets projets);

    Projets addProjets(Projets projets);

    Projets retrieveProjets(Long idProjets);

    void removeProjets(Long idProjets);
}