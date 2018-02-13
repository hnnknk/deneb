package xyz.hnnknk.deneb.dao.SystemUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.PowerSupply;
import xyz.hnnknk.deneb.model.SystemUnit;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PowerSupplyDAOImpl implements SystemUnitDAO {

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
    public PowerSupply findById(long id) {

        for(PowerSupply pow : listAll()) {
            if (id == pow.getId()) {
                return pow;
            }
        }
        return null;
    }

    @Override
    public List<PowerSupply> listAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<PowerSupply> query = sessionFactory.getCurrentSession().createQuery("from PowerSupply");
        return query.getResultList();
    }

    @Override
    public boolean isExists(SystemUnit systemUnit) {
        boolean result = false;

        for(PowerSupply pow : listAll()) {
            if (pow.getId().equals(systemUnit.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
