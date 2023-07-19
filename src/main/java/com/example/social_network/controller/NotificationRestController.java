package com.example.social_network.controller;

import com.example.social_network.model.notification.Notification;
import com.example.social_network.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/notifications")
public class NotificationRestController {
    @Autowired
    private INotificationService notificationService;

    @PostMapping()
    public ResponseEntity<Void> saveNotification(@RequestBody Notification notification) {
        notificationService.save(notification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteNotificationById(@PathVariable Long id) {
//        this.notificationService.remove(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Notification>> findAllNotificationByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(notificationService.findAllNotificationByUserId(id), HttpStatus.OK);
    }
}