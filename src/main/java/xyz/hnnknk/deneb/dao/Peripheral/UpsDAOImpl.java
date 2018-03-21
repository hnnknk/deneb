package xyz.hnnknk.deneb.dao.Peripheral;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Ups;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UpsDAOImpl implements PeripheralDAO<Ups> {

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
        return sessionFactory.getCurrentSession().get(Ups.class, id);
    }

    @Override
    public List<Ups> listAll() {
        TypedQuery<Ups> query = sessionFactory.getCurrentSession().createQuery("from Ups");
        return query.getResultList();
    }
}
