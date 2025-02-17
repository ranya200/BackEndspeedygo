package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.Notification;

import java.util.List;

public interface INotification {
    Notification createNotification(Notification n);

    List<Notification> ListNotifications();

    Notification getNotification(String id);

    void  deleteNotification(String id);

}
