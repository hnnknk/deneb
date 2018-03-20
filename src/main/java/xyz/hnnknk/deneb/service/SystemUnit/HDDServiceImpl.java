package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.HDD;

import java.util.List;
import java.util.Objects;

@Service
public class HDDServiceImpl implements SystemUnitService<HDD> {

    @Autowired
    SystemUnitDAO<HDD> HDDDAOImpl;

    @Transactional
    @Override
    public void save(HDD hdd) throws EntityExistsException {
        for(HDD h : listAll()) {
            if (Objects.equals(h, hdd)) {
                throw new EntityExistsException("A "+ hdd.toString() + " already exists!");
            }
        }
        HDDDAOImpl.save(hdd);
    }

    @Transactional
    @Override
    public void update(HDD hdd) {
        HDDDAOImpl.update(hdd);
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
        return HDDDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<HDD> listAll() {
        return HDDDAOImpl.listAll();
    }
}

