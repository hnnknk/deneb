package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.Computer;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ComputerDAOImpl implements ComputerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Computer computer) {
        sessionFactory.getCurrentSession().save(computer);
    }

    @Override
    public void update(Computer computer) {
        sessionFactory.getCurrentSession().update(computer);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(id);
    }

    @Override
    public Computer findById(long id) {
        return sessionFactory.getCurrentSession().get(Computer.class, id);
    }

    @Override
    public List<Computer> listAll() {
        TypedQuery<Computer> query = sessionFactory.getCurrentSession().createQuery("from Computer");
        return query.getResultList();
    }
}
