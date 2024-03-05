package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Repertoire;
import com.test.COCONSULT.Interfaces.RepertoireService;
import com.test.COCONSULT.Reposotories.RepertoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RepertoireServiceImpl implements RepertoireService {
    @Autowired
    RepertoireRepository repertoireRepository ;

    @Override
    public List<Repertoire> retrieveRepertoire() {
        return repertoireRepository.findAll();
    }

    @Override
    public Repertoire updateRepertoire(Repertoire repertoire) {
        return repertoireRepository.save(repertoire);
    }

    @Override
    public Repertoire addRepertoire(Repertoire repertoire) {
        return repertoireRepository.save(repertoire);
    }

    @Override
    public Repertoire retrieveRepertoire(Long idContact) {
        Optional<Repertoire> RepertoireOptional = repertoireRepository.findById(idContact);
        return RepertoireOptional.orElse(null);
    }

    @Override
    public void removeRepertoire(Long idContact) {
        repertoireRepository.deleteById(idContact);

    }
}
