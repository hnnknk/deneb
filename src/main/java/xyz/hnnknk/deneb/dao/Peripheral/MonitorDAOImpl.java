package xyz.hnnknk.deneb.dao.Peripheral;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Monitor;
import xyz.hnnknk.deneb.model.Peripheral;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MonitorDAOImpl implements PeripheralDAO<Monitor> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Monitor monitor) {
        sessionFactory.getCurrentSession().save(monitor);
    }

    @Override
    public void update(Monitor monitor) {
        sessionFactory.getCurrentSession().update(monitor);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public Monitor findById(long id) {
        return  sessionFactory.getCurrentSession().get(Monitor.class, id);
    }

    @Override
    public List<Monitor> listAll() {
        TypedQuery<Monitor> query = sessionFactory.getCurrentSession().createQuery("from Monitor");
        return query.getResultList();
    }
}
