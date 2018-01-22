package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.UpsDAO;
import xyz.hnnknk.deneb.model.Ups;

import java.util.List;

@Service
public class UpsServiceImpl implements UpsService {

    @Autowired
    UpsDAO upsDAO;

    @Transactional
    @Override
    public void save(Ups ups) {
        upsDAO.save(ups);
    }

    @Transactional
    @Override
    public void update(Ups ups) {
        upsDAO.update(ups);
    }

    @Transactional
    @Override
    public void delete(long id) {
        upsDAO.delete(id);
    }

    @Transactional
    @Override
    public Ups findById(long id) {
        return upsDAO.findById(id);
    }

    @Transactional
    @Override
    public List<Ups> listAllUpses() {
        return upsDAO.listAllUpses();
    }

    @Transactional
    @Override
    public boolean isUpsExists(Ups ups) {
        return upsDAO.isUpsExists(ups);
    }
}

