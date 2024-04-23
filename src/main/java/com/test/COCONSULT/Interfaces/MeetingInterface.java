package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Meetings;

import java.util.List;

public interface MeetingInterface {
    Meetings AddMeeting(Meetings meet);

    Meetings getMeetingById(Integer idMeeting);

    void deleteMeetingByID(Integer idMeeting);


    Meetings editMeetingByID(Integer idMeeting, Meetings updateMeeting);

    Meetings addMeetingandAffecteraunprojet(Meetings meet, String projectTitle);

    public List<Meetings> getAllMeetings();

    void affecterUserAmeet(Integer idMeeting, String username);

















}
