package xyz.hnnknk.deneb.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.hnnknk.deneb.model.HDD;

import javax.persistence.TypedQuery;
import java.util.List;

public class HDDDAOImpl implements HDDDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(HDD hdd) {
        sessionFactory.getCurrentSession().save(hdd);
    }

    @Override
    public void update(HDD hdd) {
        sessionFactory.getCurrentSession().update(hdd);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public HDD findById(long id) {

        for(HDD h : listAllHdds()) {
            if (id == h.getId()) {
                return h;
            }
        }
        return null;
    }

    @Override
    public List<HDD> listAllHdds() {
        @SuppressWarnings("unchecked")
        TypedQuery<HDD> query = sessionFactory.getCurrentSession().createQuery("from HDD");
        return query.getResultList();
    }

    @Override
    public boolean isHddExists(HDD hdd) {
        boolean result = false;

        for(HDD h : listAllHdds()) {
            if (h.getId().equals(hdd.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
