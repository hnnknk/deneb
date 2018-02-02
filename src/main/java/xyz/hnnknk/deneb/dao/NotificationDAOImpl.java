package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.model.Notification;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class NotificationDAOImpl  implements NotificationDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(Notification notification) {
        sessionFactory.getCurrentSession().save(notification);
    }

    @Override
    public void update(Notification notification) {
        sessionFactory.getCurrentSession().save(notification);
    }

    @Override
    public Notification findById(long id) {

        for(Notification note : listAllNotifications()) {
            if (id == note.getId()) {
                return note;
            }
        }
        return null;
    }

    @Override
    public List<Notification> listAllNotifications() {
        @SuppressWarnings("unchecked")
        TypedQuery<Notification> query = sessionFactory.getCurrentSession().createQuery("from Notification");
        return query.getResultList();
    }
}
