package xyz.hnnknk.deneb.dao.Peripheral;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Keyboard;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class KeyboardDAOImpl implements PeripheralDAO<Keyboard> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Keyboard keyboard) {
        sessionFactory.getCurrentSession().save(keyboard);
    }

    @Override
    public void update(Keyboard keyboard) {
        sessionFactory.getCurrentSession().update(keyboard);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public Keyboard findById(long id) {
        return sessionFactory.getCurrentSession().get(Keyboard.class, id);
    }

    @Override
    public List<Keyboard> listAll() {
        TypedQuery<Keyboard> query = sessionFactory.getCurrentSession().createQuery("from Keyboard");
        return query.getResultList();
    }
}
