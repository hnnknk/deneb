package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Backups;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BackupsDAOImpl implements BackupsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Backups backups) {
        sessionFactory.getCurrentSession().save(backups);
    }

    @Override
    public void update(Backups backups) {
        sessionFactory.getCurrentSession().update(backups);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public Backups findById(long id) {

        for(Backups key : listAllBackupses()) {
            if (id == key.getId()) {
                return key;
            }
        }
        return null;
    }

    @Override
    public List<Backups> listAllBackupses() {
        @SuppressWarnings("unchecked")
        TypedQuery<Backups> query = sessionFactory.getCurrentSession().createQuery("from Backups");
        return query.getResultList();
    }
}
