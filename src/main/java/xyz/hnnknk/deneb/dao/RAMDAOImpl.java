package xyz.hnnknk.deneb.dao;

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

        for(RAM r : listAll()) {
            if (id == r.getId()) {
                return r;
            }
        }
        return null;
    }

    @Override
    public List<RAM> listAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<RAM> query = sessionFactory.getCurrentSession().createQuery("from RAM");
        return query.getResultList();
    }

    @Override
    public boolean isExists(SystemUnit systemUnit) {
        boolean result = false;

        for(RAM r : listAll()) {
            if (r.getId().equals(systemUnit.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
