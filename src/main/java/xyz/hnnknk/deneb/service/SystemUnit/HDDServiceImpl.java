package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.SystemUnit;
import xyz.hnnknk.deneb.model.HDD;

import java.util.List;

@Service
public class HDDServiceImpl implements SystemUnitService {

    @Autowired
    SystemUnitDAO HDDDAOImpl;

    @Transactional
    @Override
    public void save(SystemUnit systemUnit) throws EntityExistsException {
        for(HDD h : listAll()) {
            if (h.getId().equals(systemUnit.getId())) {
                throw new EntityExistsException("A hdd with invNumber "+ systemUnit.getId() +" already exists!");
            }
        }
        HDDDAOImpl.save(systemUnit);
    }

    @Transactional
    @Override
    public void update(SystemUnit systemUnit) {
        HDDDAOImpl.update(systemUnit);
    }

    @Transactional
    @Override
    public void delete(long id) {
        HDDDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public HDD findById(long id) throws EntityNotFoundException {

        if(HDDDAOImpl.findById(id) == null) {
            throw new EntityNotFoundException("A hdd with " + id + " not found!");
        }
        return (HDD) HDDDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<HDD> listAll() {
        return HDDDAOImpl.listAll();
    }
}

