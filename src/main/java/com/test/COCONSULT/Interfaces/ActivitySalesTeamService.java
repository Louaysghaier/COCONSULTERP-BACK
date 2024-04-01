package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.ActivitySalesTeam;
import com.test.COCONSULT.Entity.Assignements;

import java.util.List;

public interface ActivitySalesTeamService {
    List<ActivitySalesTeam> retrieveActivitySalesTeam();
    ActivitySalesTeam updateActivitySalesTeam(ActivitySalesTeam activitySalesTeam);
    ActivitySalesTeam addActivitySalesTeam(ActivitySalesTeam activitySalesTeam);
    ActivitySalesTeam retrieveActivitySalesTeam(Long idActSale);

    void removeActivitySalesTeam(Long idActSale);
}
