package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.ActivitySalesTeam;
import com.test.COCONSULT.Entity.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> retrieveContract();
    Contract updateContract(Contract contract);
    Contract addContract(Contract contract);
    Contract retrieveContract(Long idContract);

    void removeContract(Long idContract);
}
