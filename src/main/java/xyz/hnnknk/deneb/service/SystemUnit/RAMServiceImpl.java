package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.RAM;

import java.util.List;
import java.util.Objects;

@Service
public class RAMServiceImpl implements SystemUnitService<RAM> {

    @Autowired
    SystemUnitDAO<RAM> RAMDAOImpl;

    @Transactional
    @Override
    public void save(RAM ram) throws EntityExistsException {
        for(RAM r : listAll()) {
            if (Objects.equals(r, ram)) {
                throw new EntityExistsException("A "+ ram.toString() +" already exists!");
            }
        }
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
    public RAM findById(long id) throws EntityNotFoundException {

        if(RAMDAOImpl.findById(id) == null) {
            throw new EntityNotFoundException("A ram memory with " + id + " not found!");
        }
        return RAMDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<RAM> listAll() {
        return RAMDAOImpl.listAll();
    }
}

