package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.SystemUnit;
import xyz.hnnknk.deneb.model.RAM;

import java.util.List;

@Service
public class RAMServiceImpl implements SystemUnitService {

    @Autowired
    SystemUnitDAO RAMDAOImpl;

    @Transactional
    @Override
    public void save(SystemUnit systemUnit) throws EntityExistsException {
        for(RAM r : listAll()) {
            if (r.getId().equals(systemUnit.getId())) {
                throw new EntityExistsException("A ram memory with invNumber "+ systemUnit.getId() +" already exists!");
            }
        }
        RAMDAOImpl.save(systemUnit);
    }

    @Transactional
    @Override
    public void update(SystemUnit systemUnit) {
        RAMDAOImpl.update(systemUnit);
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
        return (RAM) RAMDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<RAM> listAll() {
        return RAMDAOImpl.listAll();
    }
}

