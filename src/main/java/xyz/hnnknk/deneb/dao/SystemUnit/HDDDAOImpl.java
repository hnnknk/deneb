package xyz.hnnknk.deneb.dao.SystemUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.HDD;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HDDDAOImpl implements SystemUnitDAO<HDD> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(HDD hdd) {
        sessionFactory.getCurrentSession().save(hdd);
    }

    @Override
    public void update(HDD hdd) {
        sessionFactory.getCurrentSession().update(hdd);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public HDD findById(long id) {
        return sessionFactory.getCurrentSession().get(HDD.class, id);
    }

    @Override
    public List<HDD> listAll() {
        TypedQuery<HDD> query = sessionFactory.getCurrentSession().createQuery("from HDD");
        return query.getResultList();
    }

}
