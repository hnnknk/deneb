package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.deneb.model.PowerSupply;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PowerSupplyDAOImpl implements PowerSupplyDAO {

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

        for(PowerSupply pow : listAllPowerSupplies()) {
            if (id == pow.getId()) {
                return pow;
            }
        }
        return null;
    }

    @Override
    public List<PowerSupply> listAllPowerSupplies() {
        @SuppressWarnings("unchecked")
        TypedQuery<PowerSupply> query = sessionFactory.getCurrentSession().createQuery("from PowerSupply");
        return query.getResultList();
    }

    @Override
    public boolean isPowerSupplyExists(PowerSupply powerSupply) {
        boolean result = false;

        for(PowerSupply pow : listAllPowerSupplies()) {
            if (pow.getId().equals(powerSupply.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
