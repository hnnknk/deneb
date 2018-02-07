package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.NotificationDAO;
import xyz.hnnknk.deneb.enums.NotificationTypes;
import xyz.hnnknk.deneb.model.Notification;

import java.util.List;

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

    @Override
    public void delete(long id) {
        notificationDAO.delete(id);
    }

    @Override
    public Notification findById(long id) {
        return notificationDAO.findById(id);
    }

    @Override
    public List<Notification> listAllNots() {
        if(notificationDAO.listAllNots().isEmpty()) {
            notificationDAO.save(new Notification());
        }
        return notificationDAO.listAllNots();
    }

    @Override
    public boolean isNotificationExists(Notification notification) {
        return notificationDAO.isNotificationExists(notification);
    }


    public void checkNotifications(NotificationTypes notificationTypes, String name) {
        if(!listAllNots().isEmpty()) {
            Notification not = listAllNots().get(0);
            if(not.getMonitorCreated() && notificationTypes.equals(NotificationTypes.MONITOR)) {
                emailService.send(name, not.getEmail());
            } else if(not.getKeyboardCreated() && notificationTypes.equals(NotificationTypes.KEYBOARD)) {
                emailService.send(name, not.getEmail());
            } else if(not.getUpsCreated() && notificationTypes.equals(NotificationTypes.UPS)) {
                emailService.send(name, not.getEmail());
            } else if(not.getMouseCreated() && notificationTypes.equals(NotificationTypes.MOUSE)) {
                emailService.send(name, not.getEmail());
            } else if(not.getUserCreated() && notificationTypes.equals(NotificationTypes.USER)) {
                emailService.send(name, not.getEmail());
            }
        }
    }


}
