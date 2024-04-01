package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Interfaces.MeetingsService;
import com.test.COCONSULT.Reposotories.MeetingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class MeetingsServiceImpl implements MeetingsService {

    MeetingsRepository meetingsRepository;



    @Override
    public List<Meetings> retrieveMeet() {
        return meetingsRepository.findAll();
    }

    @Override
    public Meetings updateMeet(Meetings meetings) {
        return meetingsRepository.save(meetings);
    }

    @Override
    public Meetings ajouterMeet(Meetings meetings) {
        return meetingsRepository.save(meetings);
    }

    @Override
    public Meetings retrieveMeet(Long idMeet) {
        Optional<Meetings> meetingsOptional = meetingsRepository.findById(idMeet);
        return meetingsOptional.orElse(null);
    }
    @Override
    public void removeMeet(Long idMeet) {
        meetingsRepository.deleteById(idMeet);
    }
}
