package com.test.COCONSULT.ServiceIMP;


import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Interfaces.ProjetsService;
import com.test.COCONSULT.Reposotories.ProjetsRepository;
import com.test.COCONSULT.Services.MailProject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProjetsServiceImpl implements ProjetsService {
    @Autowired
    ProjetsRepository projetsRepository;
    @Autowired
    MailProject mailProject;


    @Override
    public List<Projets> retrieveProjets() {
        return projetsRepository.findAll();
    }

    @Override
    public Projets updateProjets(Projets projets) {
        return projetsRepository.save(projets);
    }

    /*@Override
    public Projets addProjets(Projets projets) {
        return projetsRepository.save(projets);
    }*/

    @Override
    public Projets retrieveProjets(Long idProjets) {
        return projetsRepository.findById(idProjets).orElse(null);
    }

    @Override
    public void removeProjets(Long idProjets) {
        projetsRepository.deleteById(idProjets);
    }

    @Override
    public List<Assignements> getAssignementsForProject(Long projectId) {
        return null;
    }

    @Override
    public Projets addProjets(Projets projets) {
        Projets newProjets = projetsRepository.save(projets);

        String subject = "Nouveau projet ajouté";
        String body = "Le projet " + projets.getProjetTitle() + " a été ajouté avec succès.";
        try {
            mailProject.send("destinataire@example.com", subject, body);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }

        return newProjets;
    }
}