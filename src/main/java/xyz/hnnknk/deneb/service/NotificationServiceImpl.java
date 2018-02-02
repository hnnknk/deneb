package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.NotificationDAO;
import xyz.hnnknk.deneb.enums.NotificationTypes;
import xyz.hnnknk.deneb.model.Notification;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    NotificationDAO notificationDAO;

    @Autowired
    EmailService emailService;

    @Transactional
    @Override
    public void save(Notification notification) {
        notificationDAO.save(notification);
    }

    @Transactional
    @Override
    public void update(Notification notification) {
        notificationDAO.update(notification);
    }

    @Transactional
    @Override
    public Notification getNotification() {
        if(notificationDAO.listAllNotifications().isEmpty()) {
            notificationDAO.save(new Notification());
        }
        return notificationDAO.listAllNotifications().get(0);
    }

    public void checkNotifications(NotificationTypes notificationTypes, String name) {
        if(getNotification().isMonitorCreated() && notificationTypes.equals(NotificationTypes.MONITOR)) {
            emailService.send( name);
        } else if(getNotification().isKeyboardCreated() && notificationTypes.equals(NotificationTypes.KEYBOARD)) {
            emailService.send(name);
        } else if(getNotification().isUpsCreated() && notificationTypes.equals(NotificationTypes.UPS)) {
            emailService.send(name);
        } else if(getNotification().isMouseCreated() && notificationTypes.equals(NotificationTypes.MOUSE)) {
            emailService.send(name);
        } else if(getNotification().isUserCreated() && notificationTypes.equals(NotificationTypes.USER)) {
            emailService.send( name);
        }
    }
}
