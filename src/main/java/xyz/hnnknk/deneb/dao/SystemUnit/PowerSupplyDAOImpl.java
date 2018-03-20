package xyz.hnnknk.deneb.dao.SystemUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.PowerSupply;
import xyz.hnnknk.deneb.model.SystemUnit;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PowerSupplyDAOImpl implements SystemUnitDAO<PowerSupply> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(PowerSupply powerSupply) {
        sessionFactory.getCurrentSession().save(powerSupply);
    }

    @Override
    public void update(PowerSupply powerSupply) {
        sessionFactory.getCurrentSession().update(powerSupply);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public PowerSupply findById(long id) {
        return sessionFactory.getCurrentSession().get(PowerSupply.class, id);
    }

    @Override
    public List<PowerSupply> listAll() {
        TypedQuery<PowerSupply> query = sessionFactory.getCurrentSession().createQuery("from PowerSupply");
        return query.getResultList();
    }
}
