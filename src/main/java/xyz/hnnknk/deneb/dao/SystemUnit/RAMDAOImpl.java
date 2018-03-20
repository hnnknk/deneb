package xyz.hnnknk.deneb.dao.SystemUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.RAM;
import xyz.hnnknk.deneb.model.SystemUnit;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RAMDAOImpl implements SystemUnitDAO<RAM> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(RAM ram) {
        sessionFactory.getCurrentSession().save(ram);
    }

    @Override
    public void update(RAM ram) {
        sessionFactory.getCurrentSession().update(ram);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public RAM findById(long id) {
        return sessionFactory.getCurrentSession().get(RAM.class, id);
    }

    @Override
    public List<RAM> listAll() {
        TypedQuery<RAM> query = sessionFactory.getCurrentSession().createQuery("from RAM");
        return query.getResultList();
    }
}
