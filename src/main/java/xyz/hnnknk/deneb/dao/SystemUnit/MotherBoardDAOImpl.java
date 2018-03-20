package xyz.hnnknk.deneb.dao.SystemUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.MotherBoard;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MotherBoardDAOImpl implements SystemUnitDAO<MotherBoard> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(MotherBoard motherBoard) {
        sessionFactory.getCurrentSession().save(motherBoard);
    }

    @Override
    public void update(MotherBoard motherBoard) {
        sessionFactory.getCurrentSession().update(motherBoard);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public MotherBoard findById(long id) {
        return sessionFactory.getCurrentSession().get(MotherBoard.class, id);
    }

    @Override
    public List<MotherBoard> listAll() {
        TypedQuery<MotherBoard> query = sessionFactory.getCurrentSession().createQuery("from MotherBoard");
        return query.getResultList();
    }
}
