package xyz.hnnknk.deneb.dao.Peripheral;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Mouse;
import xyz.hnnknk.deneb.model.Peripheral;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MouseDAOImpl implements PeripheralDAO {

    @Autowired
    SessionFactory sessionFactory;

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
    public Mouse findById(long id) {
        return sessionFactory.getCurrentSession().get(Mouse.class, id);
    }

    @Override
    public List<Mouse> listAll() {
        TypedQuery<Mouse> query = sessionFactory.getCurrentSession().createQuery("from Mouse");
        return query.getResultList();
    }
}
