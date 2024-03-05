package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Entity.Prospect;
import com.test.COCONSULT.Interfaces.ProspectService;
import com.test.COCONSULT.Reposotories.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProspectImpl implements ProspectService {
    @Autowired
    ProspectRepository prospectRepository ;
    @Override
    public List<Prospect> retrieveProspect() {
        return prospectRepository.findAll() ;
    }

    @Override
    public Prospect updateProspect(Prospect prospect) {
        return prospectRepository.save(prospect);
    }


    @Override
    public Prospect addProspect(Prospect prospect) {
        return prospectRepository.save(prospect);
    }



    @Override
    public Prospect retrieveProspect(Long idProspect) {
        Optional<Prospect> ProspectOptional = prospectRepository.findById(idProspect);
        return ProspectOptional.orElse(null);
    }

    @Override
    public void removeProspect(Long idProspect) {
        prospectRepository.deleteById(idProspect);
    }
}
