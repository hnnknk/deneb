package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hnnknk.deneb.model.Notification;
import xyz.hnnknk.deneb.service.NotificationService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/components/notification/", method = RequestMethod.GET)
    public ResponseEntity<List<Notification>> listAllNots() {
        List<Notification> notes = notificationService.listAllNots();
        if(notes.isEmpty()){
            return new ResponseEntity<List<Notification>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Notification>>(notes, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/notification/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> getnotification(@PathVariable("id") long id) {
        System.out.println("Fetching note with id " + id);
        Notification notification =notificationService.findById(id);
        if (notification == null) {
            System.out.println("note with id " + id + " not found");
            return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/notification/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Notification> updatenotification(@PathVariable("id") long id,@Valid @RequestBody Notification notification) {
        System.out.println("Updating " + notification.toString());

        Notification currentnotification = notificationService.findById(id);

        if (currentnotification==null) {
            System.out.println("notification with id " + id + " not found");
            return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
        }

        currentnotification.setEmail(notification.getEmail());
        currentnotification.setUpsCreated(notification.getUpsCreated());
        currentnotification.setUserCreated(notification.getUserCreated());
        currentnotification.setKeyboardCreated(notification.getKeyboardCreated());
        currentnotification.setMouseCreated(notification.getMouseCreated());
        currentnotification.setMonitorCreated(notification.getMonitorCreated());

        notificationService.update(currentnotification);
        return new ResponseEntity<Notification>(currentnotification, HttpStatus.OK);
    }
}
