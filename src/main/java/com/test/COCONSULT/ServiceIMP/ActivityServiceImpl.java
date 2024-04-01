package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Activity;
import com.test.COCONSULT.Interfaces.ActivityService;
import com.test.COCONSULT.Reposotories.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Override
    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }
    @Override
    public List<Activity> retrieveActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity updateActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity retrieveActivity(Long   idActivity) {
        return (Activity) activityRepository.findById(idActivity).orElse(null);
    }
    @Override
    public void removeActivity( Long  idActivity) {
        activityRepository.deleteById(idActivity);
    }
}
