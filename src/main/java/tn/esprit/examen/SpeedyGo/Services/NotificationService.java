package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.NotificationRepo;
import tn.esprit.examen.SpeedyGo.entities.Notification;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class NotificationService implements INotification {

    @Autowired
    private NotificationRepo notificationRepo;


    @Override
    public Notification createNotification(Notification n) {
        return notificationRepo.save(n);
    }

    @Override
    public List<Notification> ListNotifications() {
        return notificationRepo.findAll();
    }

    @Override
    public Notification getNotification(String id) {
        return notificationRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteNotification(String id) {
        notificationRepo.deleteById(id);
    }
}