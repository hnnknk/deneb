package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.enums.NotificationTypes;
import xyz.hnnknk.deneb.model.Notification;

import java.util.List;

public interface NotificationService {

    void save(Notification notification);
    void update(Notification notification);
    Notification getNotification();

    void checkNotifications(NotificationTypes notificationTypes, String name);
}
