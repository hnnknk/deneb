package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Peripheral;
import xyz.hnnknk.deneb.model.Ups;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UpsDAOImpl implements PeripheralDAO {

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
    public Ups findById(long id) {

        for(Ups key : listAll()) {
            if (id == key.getId()) {
                return key;
            }
        }
        return null;
    }

    @Override
    public List<Ups> listAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<Ups> query = sessionFactory.getCurrentSession().createQuery("from Ups");
        return query.getResultList();
    }

    @Override
    public boolean isExists(Peripheral peripheral) {

        boolean result = false;

        for(Ups u : listAll()) {
            if (u.getInvNumber().equals(peripheral.getInvNumber())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
