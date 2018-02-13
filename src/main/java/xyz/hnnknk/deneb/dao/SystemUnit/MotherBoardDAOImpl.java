package xyz.hnnknk.deneb.dao.SystemUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.MotherBoard;
import xyz.hnnknk.deneb.model.SystemUnit;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MotherBoardDAOImpl implements SystemUnitDAO {

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
    public MotherBoard findById(long id) {

        for(MotherBoard mot : listAll()) {
            if (id == mot.getId()) {
                return mot;
            }
        }
        return null;
    }

    @Override
    public List<MotherBoard> listAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<MotherBoard> query = sessionFactory.getCurrentSession().createQuery("from MotherBoard");
        return query.getResultList();
    }

    @Override
    public boolean isExists(SystemUnit systemUnit) {
        boolean result = false;

        for(MotherBoard m : listAll()) {
            if (m.getId().equals(systemUnit.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
