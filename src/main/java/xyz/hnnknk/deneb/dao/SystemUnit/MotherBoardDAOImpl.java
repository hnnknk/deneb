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

        MotherBoard m;
        m = sessionFactory.getCurrentSession().get(MotherBoard.class, id);

        return m;
    }

    @Override
    public List<MotherBoard> listAll() {
        TypedQuery<MotherBoard> query = sessionFactory.getCurrentSession().createQuery("from MotherBoard");
        return query.getResultList();
    }
}