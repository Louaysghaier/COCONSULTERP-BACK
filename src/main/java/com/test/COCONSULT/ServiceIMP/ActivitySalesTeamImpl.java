package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.ActivitySalesTeam;
import com.test.COCONSULT.Interfaces.ActivitySalesTeamService;
import com.test.COCONSULT.Reposotories.ActivitySalesTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivitySalesTeamImpl implements ActivitySalesTeamService {
    @Autowired
    ActivitySalesTeamRepository activitySalesTeamRepository ;
    @Override
    public List<ActivitySalesTeam> retrieveActivitySalesTeam() {
        return activitySalesTeamRepository.findAll() ;
    }

    @Override
    public ActivitySalesTeam updateActivitySalesTeam(ActivitySalesTeam activitySalesTeam) {
        return activitySalesTeamRepository.save(activitySalesTeam);
    }

    @Override
    public ActivitySalesTeam addActivitySalesTeam(ActivitySalesTeam activitySalesTeam) {
        return activitySalesTeamRepository.save(activitySalesTeam) ;
    }

    @Override
    public ActivitySalesTeam retrieveActivitySalesTeam(Long idActSale) {
        Optional<ActivitySalesTeam> ActivitySalesTeamOptional = activitySalesTeamRepository.findById(idActSale);
        return ActivitySalesTeamOptional.orElse(null);
    }

    @Override
    public void removeActivitySalesTeam(Long idActSale) {
        activitySalesTeamRepository.deleteById(idActSale);
    }
}
