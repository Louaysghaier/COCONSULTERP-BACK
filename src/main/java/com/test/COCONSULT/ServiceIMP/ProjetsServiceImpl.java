package com.test.COCONSULT.ServiceIMP;


import com.test.COCONSULT.Entity.Projets;
import com.test.COCONSULT.Interfaces.ProjetsService;
import com.test.COCONSULT.Reposotories.ProjetsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProjetsServiceImpl implements ProjetsService {
   ProjetsRepository projetsRepository;

    @Override
    public List<Projets> retrieveProjets() {
        return projetsRepository.findAll();
    }

    @Override
    public Projets updateProjets(Projets projets) {
        return projetsRepository.save(projets);
    }

    @Override
    public Projets addProjets(Projets projets) {
        return projetsRepository.save(projets);
    }

    @Override
    public Projets retrieveProjets(Long idProjets) {
        return projetsRepository.findById(idProjets).orElse(null);
    }

    @Override
    public void removeProjets(Long idProjets) {
        projetsRepository.deleteById(idProjets);
    }
}