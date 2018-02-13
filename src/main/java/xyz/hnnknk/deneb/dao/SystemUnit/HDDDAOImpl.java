package xyz.hnnknk.deneb.dao.SystemUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.HDD;
import xyz.hnnknk.deneb.model.SystemUnit;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HDDDAOImpl implements SystemUnitDAO {

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
    public HDD findById(long id) {

        for(HDD h : listAll()) {
            if (id == h.getId()) {
                return h;
            }
        }
        return null;
    }

    @Override
    public List<HDD> listAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<HDD> query = sessionFactory.getCurrentSession().createQuery("from HDD");
        return query.getResultList();
    }

    @Override
    public boolean isExists(SystemUnit systemUnit) {
        boolean result = false;

        for(HDD h : listAll()) {
            if (h.getId().equals(systemUnit.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
