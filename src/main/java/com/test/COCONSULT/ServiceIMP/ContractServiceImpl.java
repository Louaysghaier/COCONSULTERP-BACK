package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Interfaces.ContractService;
import com.test.COCONSULT.Reposotories.ContractRepository;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ContractServiceImpl implements ContractService {
    @Autowired ContractRepository contractRepository ;

    @Override
    public List<Contract> retrieveContract() { return contractRepository.findAll() ; }

    @Override
    public Contract updateContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract retrieveContract(Long idContract) {
        Optional<Contract> ContractOptional = contractRepository.findById(idContract);
        return ContractOptional.orElse(null);
    }

    @Override
    public void removeContract(Long idContract) {
        contractRepository.deleteById(idContract);
    }
}
