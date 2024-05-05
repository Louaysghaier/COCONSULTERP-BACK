package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.Reclamation;
import com.test.COCONSULT.Reposotories.CandidatRepository;
import com.test.COCONSULT.Reposotories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReclamationServiceImp {
    @Autowired
    ReclamationRepository reclamationRepository;

    CandidatServiceImp candidatServiceImp;
    @Autowired
CandidatRepository candidatRepository;



    @Autowired
    public ReclamationServiceImp(ReclamationRepository reclamationRepository, CandidatServiceImp candidatServiceImp) {
        this.reclamationRepository = reclamationRepository;
        this.candidatServiceImp = candidatServiceImp;
    }

    @Transactional
    public Reclamation ajouterReclamation(String contenu, String emailCandidat) {
        // Vérifier si le candidat existe déjà dans la base de données
        Candidat candidat = candidatRepository.findByEmail(emailCandidat);

        // Si le candidat n'existe pas, créer un nouveau candidat
        if (candidat == null) {
            candidat = new Candidat();
            candidat.setEmail(emailCandidat);
            candidat = candidatRepository.save(candidat);
        }

        // Créer la réclamation
        Reclamation reclamation = new Reclamation();
        reclamation.setContent(contenu);
        // Associer la réclamation au candidat
        reclamation.getCandidat().add(candidat);
        reclamation = reclamationRepository.save(reclamation);

        return reclamation;
    }
}