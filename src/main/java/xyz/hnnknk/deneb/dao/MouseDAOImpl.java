package xyz.hnnknk.deneb.dao;

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

        for(Mouse mou : listAll()) {
            if (id == mou.getId()) {
                return mou;
            }
        }
        return null;
    }

    @Override
    public List<Mouse> listAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<Mouse> query = sessionFactory.getCurrentSession().createQuery("from Mouse");
        return query.getResultList();
    }

    @Override
    public boolean isExists(Peripheral peripheral) {

        boolean result = false;

        for (Mouse m : listAll()) {
            if (m.getInvNumber().equals(peripheral.getInvNumber())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
