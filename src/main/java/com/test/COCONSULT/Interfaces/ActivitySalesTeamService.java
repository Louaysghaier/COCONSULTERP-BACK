package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.ActivitySalesTeam;

import java.util.List;

public interface ActivitySalesTeamService {
    List<ActivitySalesTeam> retrieveActivitySalesTeam();
    ActivitySalesTeam updateActivitySalesTeam(ActivitySalesTeam activitySalesTeam);
    ActivitySalesTeam addActivitySalesTeam(ActivitySalesTeam activitySalesTeam);
    ActivitySalesTeam retrieveActivitySalesTeam(Long idActSale);

    public List<ActivitySalesTeam> getActivitySalesTeamByClass(String classSalesTeam) ;

    void removeActivitySalesTeam(Long idActSale);
}