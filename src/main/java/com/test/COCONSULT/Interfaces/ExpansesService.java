package com.test.COCONSULT.Interfaces;


import com.test.COCONSULT.Entity.Expanses;

import java.util.List;

public interface ExpansesService  {

    List<Expanses> retrieveExpanses();
    Expanses updateExpanses(Expanses expanses);
    Expanses addExpanses(Expanses expanses);
    Expanses retrieveExpanses(Long idExpanses);
    void removeExpanses(Long idExpanses);
}