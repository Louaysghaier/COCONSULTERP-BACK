package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Notification;
import com.test.COCONSULT.Interfaces.NotificatinInterface;
import com.test.COCONSULT.Reposotories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceIMP implements NotificatinInterface {
@Autowired
NotificationRepository notificationRepository;
    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotification(Long id) {
        return notificationRepository.getById(id);
    }



    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
