package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Repertoire;

import java.util.List;

public interface RepertoireService {
    List<Repertoire> retrieveRepertoire();
    Repertoire updateRepertoire(Repertoire repertoire);
    Repertoire addRepertoire(Repertoire repertoire);
    Repertoire retrieveRepertoire(Long idContact);

    void removeRepertoire(Long idContact);
}
