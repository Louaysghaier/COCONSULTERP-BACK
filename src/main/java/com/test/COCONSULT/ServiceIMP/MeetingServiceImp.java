package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Entity.Projet;
import com.test.COCONSULT.Interfaces.MeetingInterface;
import com.test.COCONSULT.Reposotories.MeetingsRepository;
import com.test.COCONSULT.Reposotories.ProjetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class MeetingServiceImp implements MeetingInterface {
    @Autowired
    MeetingsRepository meetingsRepository;
    @Autowired
    ProjetRepository projetRepository;

    @Override
    public Meetings AddMeeting(Meetings meet) {
        return meetingsRepository.save(meet);
    }

    @Override
    public Meetings getMeetingById(Integer idMeeting) {
        return meetingsRepository.findMeetingsByIdMeeting(idMeeting);
    }

    @Override
    @Transactional
    public void deleteMeetingByID(Integer idMeeting) {
        meetingsRepository.deleteMeetingsByIdMeeting(idMeeting);
    }

    @Override
    public Meetings editMeetingByID(Integer idMeeting, Meetings updateMeeting) {
        Meetings existingMeet = meetingsRepository.findMeetingsByIdMeeting(idMeeting);
        if (existingMeet != null) {
            existingMeet.setDateMeeting(updateMeeting.getDateMeeting());
            existingMeet.setTypeMeet(updateMeeting.getTypeMeet());
            existingMeet.setProjets(updateMeeting.getProjets());
            return meetingsRepository.save(existingMeet);

        } else {
            System.out.println("Meeting dos not exist");
            return null;
        }

    }

    @Override
    public Meetings addMeetingandAffecteraunprojet(Meetings meet, String projectTitle) {
        Projet projet=projetRepository.getProjetByProjectTitle(projectTitle);
        meetingsRepository.save(meet);
        meet.setProjets(projet);
        return meetingsRepository.save(meet);
    }

    @Override
    public List<Meetings> getAllMeetings() {
        return meetingsRepository.findAll();
    }


}

