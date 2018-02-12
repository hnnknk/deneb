package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.hnnknk.deneb.model.MotherBoard;

import javax.persistence.TypedQuery;
import java.util.List;

public class MotherBoardDAOImpl implements MotherBoardDAO{
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

        for(MotherBoard mot : listAllMotherBoards()) {
            if (id == mot.getId()) {
                return mot;
            }
        }
        return null;
    }

    @Override
    public List<MotherBoard> listAllMotherBoards() {
        @SuppressWarnings("unchecked")
        TypedQuery<MotherBoard> query = sessionFactory.getCurrentSession().createQuery("from MotherBoard");
        return query.getResultList();
    }

    @Override
    public boolean isMotherBoardExists(MotherBoard motherBoard) {
        boolean result = false;

        for(MotherBoard m : listAllMotherBoards()) {
            if (m.getId().equals(motherBoard.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
