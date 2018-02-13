package xyz.hnnknk.deneb.dao.Peripheral;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Keyboard;
import xyz.hnnknk.deneb.model.Peripheral;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class KeyboardDAOImpl implements PeripheralDAO {

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
    public Keyboard findById(long id) {

        for(Keyboard key : listAll()) {
            if (id == key.getId()) {
                return key;
            }
        }
        return null;
    }

    @Override
    public List<Keyboard> listAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<Keyboard> query = sessionFactory.getCurrentSession().createQuery("from Keyboard");
        return query.getResultList();
    }

    @Override
    public boolean isExists(Peripheral peripheral) {
        boolean result = false;

        for(Keyboard k : listAll()) {
            if (k.getInvNumber().equals(peripheral.getInvNumber())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
