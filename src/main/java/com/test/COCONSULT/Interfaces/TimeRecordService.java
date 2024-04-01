package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.TimeRecord;

import java.util.List;

public interface TimeRecordService  {
    List<TimeRecord> retrieveTimeRec();

    TimeRecord updateTimeRec(TimeRecord timeRecord);

    TimeRecord ajouterTimeRec(TimeRecord timeRecord);

    TimeRecord retrieveTimeRecord(Long idTimeRec);

    void removeTimeRec(Long Ticket);
}