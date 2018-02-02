package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.hnnknk.deneb.model.Notification;
import xyz.hnnknk.deneb.service.NotificationService;
import xyz.hnnknk.deneb.enums.NotificationTypes;
import xyz.hnnknk.deneb.model.User;
import xyz.hnnknk.deneb.service.UserService;

import javax.validation.Valid;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/components/notification}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> getNotification() {
        System.out.println("Fetching notifications");
        Notification notification = notificationService.getNotification();

        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    @RequestMapping(value = "/components/notification/}", method = RequestMethod.PUT)
    public ResponseEntity<Notification> updateNotification(@Valid @RequestBody Notification notification) {
        System.out.println("Updating notification");

        Notification current = notificationService.getNotification();

        current.setMonitorCreated(notification.isMonitorCreated());
        current.setMouseCreated(notification.isMouseCreated());
        current.setKeyboardCreated(notification.isKeyboardCreated());
        current.setUpsCreated(notification.isUpsCreated());
        current.setUserCreated(notification.isUserCreated());

        notificationService.update(current);

        return new ResponseEntity<Notification>(current, HttpStatus.OK);
    }
}
