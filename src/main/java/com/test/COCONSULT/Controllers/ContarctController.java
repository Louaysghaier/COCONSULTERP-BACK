package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Interfaces.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("Contract")
@RestController
@AllArgsConstructor
public class ContarctController{
    private final ContractService contractService ;

    @GetMapping("/GetAllContract")
    public ResponseEntity<List<Contract>> retrieveContract() {
        List<Contract> ContractList = contractService.retrieveContract();
        return ResponseEntity.ok(ContractList);
    }

    @GetMapping("/GetContractByID/{id}")
    public ResponseEntity<Contract> retrieveContract(@PathVariable("id") Long idContract ) {
        Contract contract =  contractService.retrieveContract(idContract);
        if (contract != null) {
            return ResponseEntity.ok(contract);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/ajouterContract")
    public Contract addContrat(@RequestBody Contract contract ) {
        return contractService.addContract(contract);

    }

    @PutMapping("/updateContract/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable("id") Long idContract, @RequestBody Contract contract) {
        contract.setIdContract(idContract); // Set the ID from the path variable
        Contract updatedConract = contractService.updateContract(contract);
        if (updatedConract != null) {
            return ResponseEntity.ok(updatedConract);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/RemoveContract/{id}")
    public ResponseEntity<Void> removeContract(@PathVariable("id") Long idContract) {
        contractService.removeContract(idContract);
        return ResponseEntity.noContent().build();
    }

}
