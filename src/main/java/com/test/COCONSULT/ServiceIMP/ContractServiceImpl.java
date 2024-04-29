package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Repertoire;
import com.test.COCONSULT.Interfaces.ContractService;
import com.test.COCONSULT.Reposotories.ContractRepository;
import com.test.COCONSULT.Reposotories.RepertoireRepository;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ContractServiceImpl implements ContractService {
    @Autowired ContractRepository contractRepository ;
    @Autowired RepertoireRepository repertoireRepository ;

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
    public Contract addContractAffectRep(Contract contract, Long repertoireId) {
        Repertoire repertoire = repertoireRepository.findById(repertoireId).orElse(null);
        if (repertoire != null) {
            contract.setRepertoire(repertoire);
            // Automatically set repertoireContact to the Contact attribute of Repertoire
            contract.setRepertoireContact(repertoire.getContact());
            return contractRepository.save(contract);
        } else {
            return null;
        }
    }

    @Override
    public Contract updateContractAffectRep(Contract updatedContract, Long contractId, Long repertoireId) {
        // Retrieve the existing contract from the database
        Optional<Contract> optionalContract = contractRepository.findById(contractId);

        if (optionalContract.isPresent()) {
            Contract existingContract = optionalContract.get();

            // Update contract details with the new values
            existingContract.setDescription(updatedContract.getDescription());
            existingContract.setDateContract(updatedContract.getDateContract());
            existingContract.setMontant(updatedContract.getMontant());
            existingContract.setNbreTranche(updatedContract.getNbreTranche());
            existingContract.setEtape(updatedContract.getEtape());
            // Update other fields as needed

            // Update the associated repertoire
            Repertoire repertoire = repertoireRepository.findById(repertoireId).orElse(null);
            if (repertoire != null) {
                existingContract.setRepertoire(repertoire);
                // Automatically set repertoireContact to the Contact attribute of Repertoire
                existingContract.setRepertoireContact(repertoire.getContact());
            }

            // Save the updated contract
            return contractRepository.save(existingContract);
        } else {
            // Contract with the given ID not found
            return null;
        }
    }



    @Override
    public Contract retrieveContract(Long idContract) {
        Optional<Contract> ContractOptional = contractRepository.findById(idContract);
        return ContractOptional.orElse(null);
    }

    @Override
    public String getRepertoireContactByContractId(Long idContract) {

        Optional<Contract> optionalContract = contractRepository.findById(idContract);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            Repertoire repertoire = contract.getRepertoire();
            if (repertoire != null) {
                return repertoire.getContact();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Contract> retrieveContractsWithRepertoireContact() {
        List<Contract> contracts = contractRepository.findAll();

        // Populate repertoireContact for each contract
        for (Contract contract : contracts) {
            String repertoireContact = getRepertoireContactByContractId(contract.getIdContract());
            contract.setRepertoireContact(repertoireContact);
        }

        return contracts;
    }

    @Override
    public void removeContract(Long idContract) {
        contractRepository.deleteById(idContract);
    }
}