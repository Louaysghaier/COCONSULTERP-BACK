package com.test.COCONSULT.Controllers;


import com.test.COCONSULT.Entity.Activity;
import com.test.COCONSULT.Interfaces.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("activity")
@AllArgsConstructor
@RestController
public class ActivityController {

    ActivityService activityService;


    // retrieve all activities
    @GetMapping("/getAllActivities")
    public List<Activity> retrieveActivities() {
        return activityService.retrieveActivities();
    }


    //ajouter les activities
    @PostMapping("/ajouterActivity")
    public Activity addActivity(@RequestBody Activity activity) {
        return activityService.addActivity(activity);
    }

    // update activities
    @PutMapping("/updateActv/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable("id") Long id, @RequestBody Activity activity) {
        Activity existingActivity = activityService.findById(id).orElse(null);
        if (existingActivity != null) {
            activity.setIdActivity(id);
            return ResponseEntity.ok(activityService.updateActivity(activity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/retrieveActv/{id}")
    public ResponseEntity<Activity> retrieveActivity(@PathVariable("id") Long id) {
        Activity activity = activityService.findById(id).orElse(null);
        if (activity != null) {
            return ResponseEntity.ok(activity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/removeActv/{id}")
    public ResponseEntity<Void> removeActivity(@PathVariable("id") Long id) {
        activityService.removeActivity(id);
        return ResponseEntity.noContent().build();
    }
}