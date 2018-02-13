package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.PeripheralDAO;
import xyz.hnnknk.deneb.model.Ups;

import java.util.List;

@Service
public class UpsServiceImpl implements UpsService {

    @Autowired
    PeripheralDAO upsDAOImpl;

    @Transactional
    @Override
    public void save(Ups ups) {
        upsDAOImpl.save(ups);
    }

    @Transactional
    @Override
    public void update(Ups ups) {
        upsDAOImpl.update(ups);
    }

    @Transactional
    @Override
    public void delete(long id) {
        upsDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public Ups findById(long id) {
        return (Ups) upsDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Ups> listAllUpses() {
        return upsDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isUpsExists(Ups ups) {
        return upsDAOImpl.isExists(ups);
    }
}

