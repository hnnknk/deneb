package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Mouse;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MouseDAOImpl implements MouseDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(Mouse mouse) {
        sessionFactory.getCurrentSession().save(mouse);
    }

    @Override
    public void update(Mouse mouse) {
        sessionFactory.getCurrentSession().update(mouse);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public Mouse findById(long id) {

        for(Mouse mou : listAllMouses()) {
            if (id == mou.getId()) {
                return mou;
            }
        }
        return null;
    }

    @Override
    public List<Mouse> listAllMouses() {
        @SuppressWarnings("unchecked")
        TypedQuery<Mouse> query = sessionFactory.getCurrentSession().createQuery("from Mouse");
        return query.getResultList();
    }

    @Override
    public boolean isMouseExists(Mouse mouse) {
        for(Mouse m : listAllMouses()) {
            if (m.equals(mouse)) {
                return true;
            }
        }
        return false;
    }
}
