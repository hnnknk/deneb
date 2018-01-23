package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Keyboard;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class KeyboardDAOImpl implements KeyboardDAO {

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

        for(Keyboard key : listAllKeyboards()) {
            if (id == key.getId()) {
                return key;
            }
        }
        return null;
    }

    @Override
    public List<Keyboard> listAllKeyboards() {
        @SuppressWarnings("unchecked")
        TypedQuery<Keyboard> query = sessionFactory.getCurrentSession().createQuery("from Keyboard");
        return query.getResultList();
    }

    @Override
    public boolean isKeyboardExists(Keyboard keyboard) {
        for(Keyboard k : listAllKeyboards()) {
            if (k.equals(keyboard)) {
                return true;
            }
        }
        return false;
    }
}
