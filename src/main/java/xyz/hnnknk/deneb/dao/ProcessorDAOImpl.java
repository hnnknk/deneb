package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Processor;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProcessorDAOImpl implements ProcessorDAO{
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

        for(Processor proc : listAllProcessors()) {
            if (id == proc.getId()) {
                return proc;
            }
        }
        return null;
    }

    @Override
    public List<Processor> listAllProcessors() {
        @SuppressWarnings("unchecked")
        TypedQuery<Processor> query = sessionFactory.getCurrentSession().createQuery("from Processor");
        return query.getResultList();
    }

    @Override
    public boolean isProcessorExists(Processor processor) {
        boolean result = false;

        for(Processor proc : listAllProcessors()) {
            if (proc.getId().equals(processor.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
