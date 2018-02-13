package xyz.hnnknk.deneb.dao;

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

        for(Processor proc : listAll()) {
            if (id == proc.getId()) {
                return proc;
            }
        }
        return null;
    }

    @Override
    public List<Processor> listAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<Processor> query = sessionFactory.getCurrentSession().createQuery("from Processor");
        return query.getResultList();
    }

    @Override
    public boolean isExists(SystemUnit systemUnit) {
        boolean result = false;

        for(Processor proc : listAll()) {
            if (proc.getId().equals(systemUnit.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
