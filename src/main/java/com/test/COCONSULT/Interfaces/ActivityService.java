package com.test.COCONSULT.Interfaces;


import com.test.COCONSULT.Entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    Optional<Activity> findById(Long id);
    List<Activity> retrieveActivities();
    Activity updateActivity(Activity activity);
    Activity addActivity(Activity activity);
    Object retrieveActivity(Long idActivity);
    void removeActivity(Long idActivity);
}