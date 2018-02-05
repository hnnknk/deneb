package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.Notification;

import java.util.List;

public interface NotificationDAO {

    void save(Notification notification);
    void update(Notification notification);
    void delete(long id);

    Notification findById(long id);

    List<Notification> listAllNots();

    boolean isNotificationExists(Notification notification);
}
