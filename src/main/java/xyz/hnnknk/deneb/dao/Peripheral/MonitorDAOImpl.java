package xyz.hnnknk.deneb.dao.Peripheral;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Monitor;
import xyz.hnnknk.deneb.model.Peripheral;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MonitorDAOImpl implements PeripheralDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Peripheral peripheral) {
        sessionFactory.getCurrentSession().save(peripheral);
    }

    @Override
    public void update(Peripheral peripheral) {
        sessionFactory.getCurrentSession().update(peripheral);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public Monitor findById(long id) {

        Monitor m;
        m = sessionFactory.getCurrentSession().get(Monitor.class, id);

        return  m;
    }

    @Override
    public List<Monitor> listAll() {
        TypedQuery<Monitor> query = sessionFactory.getCurrentSession().createQuery("from Monitor");
        return query.getResultList();
    }
}
