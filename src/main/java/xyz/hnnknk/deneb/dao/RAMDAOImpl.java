package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.RAM;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RAMDAOImpl implements RAMDAO{

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

        for(RAM r : listAllRams()) {
            if (id == r.getId()) {
                return r;
            }
        }
        return null;
    }

    @Override
    public List<RAM> listAllRams() {
        @SuppressWarnings("unchecked")
        TypedQuery<RAM> query = sessionFactory.getCurrentSession().createQuery("from RAM");
        return query.getResultList();
    }

    @Override
    public boolean isRamExists(RAM ram) {
        boolean result = false;

        for(RAM r : listAllRams()) {
            if (r.getId().equals(ram.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
