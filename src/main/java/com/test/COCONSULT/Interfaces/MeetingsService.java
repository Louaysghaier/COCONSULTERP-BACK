package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Meetings;

import java.util.List;

public interface MeetingsService {
    List<Meetings> retrieveMeet();
    Meetings updateMeet(Meetings meetings);
    Meetings ajouterMeet(Meetings meetings);
    List<Meetings> getMeetingsForProject(Long projectId);

    Meetings retrieveMeet(Long idMeet);

    void removeMeet(Long idMeet);

}