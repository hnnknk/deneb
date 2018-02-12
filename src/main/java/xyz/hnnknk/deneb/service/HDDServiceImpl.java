package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.HDDDAO;
import xyz.hnnknk.deneb.model.HDD;

import java.util.List;

@Service
public class HDDServiceImpl implements HDDService{

    @Autowired
    HDDDAO hddDAO;

    @Transactional
    @Override
    public void save(HDD hdd) {
        hddDAO.save(hdd);
    }

    @Transactional
    @Override
    public void update(HDD hdd) {
        hddDAO.update(hdd);
    }

    @Transactional
    @Override
    public void delete(long id) {
        hddDAO.delete(id);
    }

    @Transactional
    @Override
    public HDD findById(long id) {
        return hddDAO.findById(id);
    }

    @Transactional
    @Override
    public List<HDD> listAllHdds() {
        return hddDAO.listAllHdds();
    }

    @Transactional
    @Override
    public boolean isHddExists(HDD hdd) {
        return hddDAO.isHddExists(hdd);
    }
}
