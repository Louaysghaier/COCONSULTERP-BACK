package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Interfaces.MeetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
public class MeetingsController {

    private final MeetingsService meetingsService;

    @Autowired
    public MeetingsController(MeetingsService meetingsService) {
        this.meetingsService = meetingsService;
    }

    @GetMapping("getAllMeeting")
    public ResponseEntity<List<Meetings>> retrieveMeet() {
        List<Meetings> meetingsList = meetingsService.retrieveMeet();
        return ResponseEntity.ok(meetingsList);
    }

    @GetMapping("/getMeetById/{idMeet}")
    public ResponseEntity<Meetings> retrieveMeet(@PathVariable("idMeet") Long idMeet) {
        Meetings meetings = meetingsService.retrieveMeet(idMeet);
        if (meetings != null) {
            return ResponseEntity.ok(meetings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterMeet")
    public ResponseEntity<Meetings> ajouterMeet(@RequestBody Meetings meetings) {
        Meetings savedMeetings = meetingsService.ajouterMeet(meetings);
        return new ResponseEntity<>(savedMeetings, HttpStatus.CREATED);
    }

    @PutMapping("/updateMeet/{idMeet}")
    public ResponseEntity<Meetings> updateMeet(@PathVariable("idMeet") Long idMeet, @RequestBody Meetings meetings) {
        meetings.setIdMeeting(idMeet); // Set the ID from the path variable
        Meetings updatedMeetings = meetingsService.updateMeet(meetings);
        if (updatedMeetings != null) {
            return ResponseEntity.ok(updatedMeetings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteMeet/{idMeet}")
    public ResponseEntity<Void> removeMeet(@PathVariable("idMeet") Long idMeet) {
        meetingsService.removeMeet(idMeet);
        return ResponseEntity.noContent().build();
    }
}
