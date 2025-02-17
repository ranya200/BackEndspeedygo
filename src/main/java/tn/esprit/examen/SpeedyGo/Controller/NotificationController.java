package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.INotification;
import tn.esprit.examen.SpeedyGo.entities.Notification;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    INotification NotificationImpl;


    @GetMapping("/listnotifications")
    public List<Notification> getAllNotifications() {
        return NotificationImpl.ListNotifications();
    }


    @GetMapping("/getNotification/{id}")
    public Notification getNotification(@PathVariable("id") String id) {
        return NotificationImpl.getNotification(id);
    }

    @PostMapping("/createNotification")
    public Notification createNotification(@RequestBody Notification n) {
        return NotificationImpl.createNotification(n);
    }


    @DeleteMapping("deleteNotification/{id}")
    public void deleteAd(@PathVariable("id") String id) {
        NotificationImpl.deleteNotification(id);
    }
}