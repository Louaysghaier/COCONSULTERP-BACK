
package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.ActivitySalesTeam;
import com.test.COCONSULT.Interfaces.ActivitySalesTeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("ActivitySalesTeam")
@RestController
@AllArgsConstructor

public class ActivitySalesTeamController {
    private final ActivitySalesTeamService activitySalesTeamService ;
    @GetMapping("/GetAllActSalesTeam")
    public ResponseEntity<List<ActivitySalesTeam>> retrieveActivitySalesTeam() {
        List<ActivitySalesTeam> ActivitySalesTeamList = activitySalesTeamService.retrieveActivitySalesTeam();
        return ResponseEntity.ok(ActivitySalesTeamList);
    }

    @GetMapping("/GetActSalesTeamByID/{id}")
    public ResponseEntity<ActivitySalesTeam> retrieveActivitySalesTeam(@PathVariable("id") Long idActSale) {
        ActivitySalesTeam activitySalesTeam = activitySalesTeamService.retrieveActivitySalesTeam(idActSale);
        if (activitySalesTeam != null) {
            return ResponseEntity.ok(activitySalesTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/ajouterActSalesTeam")
    public ActivitySalesTeam addActivitySalesTeam(@RequestBody ActivitySalesTeam activitySalesTeam) {
        return activitySalesTeamService.addActivitySalesTeam(activitySalesTeam);

    }

    @PutMapping("/updateActSalesTeam/{id}")
    public ResponseEntity<ActivitySalesTeam> updateActivitySalesTeam(@PathVariable("id") Long idActSales, @RequestBody ActivitySalesTeam activitySalesTeam) {
        activitySalesTeam.setIdActSale(idActSales); // Set the ID from the path variable
        ActivitySalesTeam updatedActivitySalesTeam = activitySalesTeamService.updateActivitySalesTeam(activitySalesTeam);
        if (updatedActivitySalesTeam != null) {
            return ResponseEntity.ok(updatedActivitySalesTeam);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/RemoveActSales/{id}")
    public ResponseEntity<Void> removeActivitySalesTeam(@PathVariable("id") Long idActSales) {
        activitySalesTeamService.removeActivitySalesTeam(idActSales);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/GetActivitySalesTeamByClass/{classSalesTeam}")
    public ResponseEntity<List<ActivitySalesTeam>> getActivitySalesTeamByClass(@PathVariable String classSalesTeam) {
        List<ActivitySalesTeam> activitySalesTeamList = activitySalesTeamService.getActivitySalesTeamByClass(classSalesTeam);
        return ResponseEntity.ok(activitySalesTeamList);
    }

}
