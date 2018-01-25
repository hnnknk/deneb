package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Monitor;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MonitorDAOImpl implements MonitorDAO {

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

        for(Monitor mon : listAllMonitors()) {
            if (id == mon.getId()) {
                return mon;
            }
        }
        return null;
    }

    @Override
    public List<Monitor> listAllMonitors() {
        @SuppressWarnings("unchecked")
        TypedQuery<Monitor> query = sessionFactory.getCurrentSession().createQuery("from Monitor");
        return query.getResultList();
    }

    @Override
    public boolean isMonitorExists(Monitor monitor) {
        for(Monitor mon : listAllMonitors()) {
            if (mon.equals(monitor)) {
                return true;
            }
        }
        return false;
    }
}
