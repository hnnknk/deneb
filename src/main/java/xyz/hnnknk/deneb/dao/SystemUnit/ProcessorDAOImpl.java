package xyz.hnnknk.deneb.dao.SystemUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Processor;
import xyz.hnnknk.deneb.model.SystemUnit;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProcessorDAOImpl implements SystemUnitDAO {

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
    public Processor findById(long id) {

        Processor p;
        p = sessionFactory.getCurrentSession().get(Processor.class, id);

        return p;
    }

    @Override
    public List<Processor> listAll() {
        TypedQuery<Processor> query = sessionFactory.getCurrentSession().createQuery("from Processor");
        return query.getResultList();
    }
}
