package com.test.COCONSULT.Tasks;

import com.mysql.cj.log.Log;
import com.test.COCONSULT.Controllers.PointageController;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Reposotories.UserRepository;
import com.test.COCONSULT.ServiceIMP.SalaireServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class ScheduledTasks {

    private final UserRepository userRepository;

    @Autowired
    PointageController pointageController;

    @Autowired
    SalaireServiceIMP salaireServiceIMP;

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

    @Scheduled(cron = "15 3 10 * * *")
    public void sendReminderEmails10() {
        //console log
        System.out.println("Sending reminder emails");
        pointageController.sendReminderEmail();
    }


    @Scheduled(cron = "15 3 14 * * *")
    public void sendReminderEmails12() {
        //console log
        System.out.println("Sending reminder emails");
        pointageController.sendReminderEmail();
    }

    @Scheduled(cron = "15 0 0 1 * *")
    public void addSalaire() {
        salaireServiceIMP.addSalaireToUser();
    }

}
