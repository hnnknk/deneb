package xyz.hnnknk.deneb.dao.SystemUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Processor;
import xyz.hnnknk.deneb.model.SystemUnit;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProcessorDAOImpl implements SystemUnitDAO<Processor> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Processor processor) {
        sessionFactory.getCurrentSession().save(processor);
    }

    @Override
    public void update(Processor processor) {
        sessionFactory.getCurrentSession().update(processor);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public Processor findById(long id) {
        return sessionFactory.getCurrentSession().get(Processor.class, id);
    }

    @Override
    public List<Processor> listAll() {
        TypedQuery<Processor> query = sessionFactory.getCurrentSession().createQuery("from Processor");
        return query.getResultList();
    }
}
