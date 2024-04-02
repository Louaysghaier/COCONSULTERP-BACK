package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Interfaces.MeetingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Meeting")
@CrossOrigin("*")
public class MeetingControlleur {
    @Autowired
    MeetingInterface meetingInterface;
    @PostMapping("/addmeet")
    public Meetings AddMeeting(@RequestBody Meetings meet) {
        return meetingInterface.AddMeeting(meet);
    }
    @GetMapping("/getMeetingById/{idMeeting}")
    public Meetings getMeetingById(@PathVariable("idMeeting") Integer idMeeting) {
        return meetingInterface.getMeetingById(idMeeting);
    }
    @GetMapping("/getAllMeetings")
    public List<Meetings> getAllMeetings() {
        return meetingInterface.getAllMeetings();
    }


    @DeleteMapping("/deleteMeetingByID/{idMeeting}")
    public void deleteMeetingByID(@PathVariable("idMeeting") Integer idMeeting) {
        meetingInterface.deleteMeetingByID(idMeeting);
    }
    @PutMapping("/editMeetingByID/{idMeeting}")
    public Meetings editMeetingByID(@PathVariable("idMeeting") Integer idMeeting,@RequestBody Meetings updateMeeting) {
        return meetingInterface.editMeetingByID(idMeeting, updateMeeting);
    }

    @PostMapping("/addMeetingandAffecteraunprojet/{projectTitle}")
    public Meetings addMeetingandAffecteraunprojet(@RequestBody Meetings meet,@PathVariable("projectTitle") String projectTitle) {
        return meetingInterface.addMeetingandAffecteraunprojet(meet, projectTitle);
    }
}
