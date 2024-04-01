package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Assignements;

import java.util.List;

public interface AssignementsService  {

    List<Assignements> retrieveAssignements();
    Assignements updateAssignements(Assignements assignements);
    Assignements addAssignements(Assignements assignements);
    Assignements retrieveAssignements(Long idAssignements);

    void removeAssignements(Long idAssignements);
}