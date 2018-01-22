package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Ups;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UpsDAOImpl implements UpsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Ups ups) {
        sessionFactory.getCurrentSession().save(ups);
    }

    @Override
    public void update(Ups ups) {
        sessionFactory.getCurrentSession().update(ups);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public Ups findById(long id) {

        for(Ups key : listAllUpses()) {
            if (id == key.getId()) {
                return key;
            }
        }
        return null;
    }

    @Override
    public List<Ups> listAllUpses() {
        @SuppressWarnings("unchecked")
        TypedQuery<Ups> query = sessionFactory.getCurrentSession().createQuery("from Ups");
        return query.getResultList();
    }
}
