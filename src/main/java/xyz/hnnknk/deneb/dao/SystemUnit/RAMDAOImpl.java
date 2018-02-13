package xyz.hnnknk.deneb.dao.SystemUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.RAM;
import xyz.hnnknk.deneb.model.SystemUnit;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RAMDAOImpl implements SystemUnitDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(SystemUnit systemUnit) {
        sessionFactory.getCurrentSession().save(systemUnit);
    }

    @Override
    public void update(SystemUnit systemUnit) {
        sessionFactory.getCurrentSession().update(systemUnit);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public RAM findById(long id) {

        RAM r;
        r = sessionFactory.getCurrentSession().get(RAM.class, id);

        return r;
    }

    @Override
    public List<RAM> listAll() {
        TypedQuery<RAM> query = sessionFactory.getCurrentSession().createQuery("from RAM");
        return query.getResultList();
    }
}
