package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnitDAO;
import xyz.hnnknk.deneb.model.RAM;

import java.util.List;

@Service
public class RAMServiceImpl implements RAMService{

    @Autowired
    SystemUnitDAO RAMDAOImpl;

    @Transactional
    @Override
    public void save(RAM ram) {
        RAMDAOImpl.save(ram);
    }

    @Transactional
    @Override
    public void update(RAM ram) {
        RAMDAOImpl.update(ram);
    }

    @Transactional
    @Override
    public void delete(long id) {
        RAMDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public RAM findById(long id) {
        return (RAM) RAMDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<RAM> listAllRams() {
        return RAMDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isRamExists(RAM ram) {
        return RAMDAOImpl.isExists(ram);
    }
}
