package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Interfaces.AssignementsService;
import com.test.COCONSULT.Reposotories.AssignementsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AssignementsServiceImpl implements AssignementsService {

    AssignementsRepository assignementsRepository;
    @Override
    public List<Assignements> retrieveAssignements() {
        return assignementsRepository.findAll();
    }

    @Override
    public Assignements updateAssignements(Assignements assignements) {
        return assignementsRepository.save(assignements);
    }

    @Override
    public Assignements addAssignements(Assignements assignements) {
        return assignementsRepository.save(assignements);
    }


    @Override
    public Assignements retrieveAssignements(Long idAssignements) {
        Optional<Assignements> assignementsOptional = assignementsRepository.findById(idAssignements);
        return assignementsOptional.orElse(null);
    }

    @Override
    public void removeAssignements(Long idAssignements) {
        assignementsRepository.deleteById(idAssignements);
    }
}