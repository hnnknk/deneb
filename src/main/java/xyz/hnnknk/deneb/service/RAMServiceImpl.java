package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.RAMDAO;
import xyz.hnnknk.deneb.model.RAM;

import java.util.List;

@Service
public class RAMServiceImpl implements RAMService{

    @Autowired
    RAMDAO ramDAO;

    @Transactional
    @Override
    public void save(RAM ram) {
        ramDAO.save(ram);
    }

    @Transactional
    @Override
    public void update(RAM ram) {
        ramDAO.update(ram);
    }

    @Transactional
    @Override
    public void delete(long id) {
        ramDAO.delete(id);
    }

    @Transactional
    @Override
    public RAM findById(long id) {
        return ramDAO.findById(id);
    }

    @Transactional
    @Override
    public List<RAM> listAllRams() {
        return ramDAO.listAllRams();
    }

    @Transactional
    @Override
    public boolean isRamExists(RAM ram) {
        return ramDAO.isRamExists(ram);
    }
}
