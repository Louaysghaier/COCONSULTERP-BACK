package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Entity.Prospect;

import java.util.List;

public interface ProspectService {
    List<Prospect> retrieveProspect();
    Prospect updateProspect(Prospect prospect);
    Prospect addProspect(Prospect prospect);
    Prospect retrieveProspect(Long idProspect);

    void removeProspect(Long idProspect);
}
