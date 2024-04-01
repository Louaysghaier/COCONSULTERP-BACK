package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.TimeRecord;
import com.test.COCONSULT.Interfaces.TimeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-records")
public class TimeRecordController {

    private final TimeRecordService timeRecordService;

    @Autowired
    public TimeRecordController(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    @GetMapping("getAllTimeRec")
    public ResponseEntity<List<TimeRecord>> retrieveTimeRecords() {
        List<TimeRecord> timeRecords = timeRecordService.retrieveTimeRec();
        return ResponseEntity.ok(timeRecords);
    }

    @GetMapping("/getTimeRecById/{idTimeRec}")
    public ResponseEntity<TimeRecord> retrieveTimeRecord(@PathVariable("idTimeRec") Long idTimeRec) {
        TimeRecord timeRecord = timeRecordService.retrieveTimeRecord(idTimeRec);
        if (timeRecord != null) {
            return ResponseEntity.ok(timeRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterTimeRec")
    public ResponseEntity<TimeRecord> addTimeRecord(@RequestBody TimeRecord timeRecord) {
        TimeRecord savedTimeRecord = timeRecordService.ajouterTimeRec(timeRecord);
        return new ResponseEntity<>(savedTimeRecord, HttpStatus.CREATED);
    }

    @PutMapping("/updateTimeRec/{idTimeRec}")
    public ResponseEntity<TimeRecord> updateTimeRecord(@PathVariable("idTimeRec") Long idTimeRec, @RequestBody TimeRecord timeRecord) {
        timeRecord.setIdTimeRec(idTimeRec); // Set the ID from the path variable
        TimeRecord updatedTimeRecord = timeRecordService.updateTimeRec(timeRecord);
        if (updatedTimeRecord != null) {
            return ResponseEntity.ok(updatedTimeRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteTimeRec/{idTimeRec}")
    public ResponseEntity<Void> removeTimeRecord(@PathVariable("idTimeRec") Long idTimeRec) {
        timeRecordService.removeTimeRec(idTimeRec);
        return ResponseEntity.noContent().build();
    }
}
