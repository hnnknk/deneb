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

        for(Monitor mon : listAll()) {
            if (id == mon.getId()) {
                return mon;
            }
        }
        return null;
    }

    @Override
    public List<Monitor> listAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<Monitor> query = sessionFactory.getCurrentSession().createQuery("from Monitor");
        return query.getResultList();
    }

    @Override
    public boolean isExists(Peripheral peripheral) {

        boolean result = false;

        for(Monitor mon : listAll()) {
            if (mon.getInvNumber().equals(peripheral.getInvNumber())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
