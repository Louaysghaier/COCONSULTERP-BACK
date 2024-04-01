package com.test.COCONSULT.Tasks;

import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final UserRepository userRepository;

    public ScheduledTasks(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "15 0 0 1 * *")
    public void addMonthlyConge() {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            user.addMonthlyConge();
            userRepository.save(user);
        }
    }
}
