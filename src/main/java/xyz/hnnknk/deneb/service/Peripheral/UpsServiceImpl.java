package xyz.hnnknk.deneb.service.Peripheral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Ups;

import java.util.List;
import java.util.Objects;

@Service
public class UpsServiceImpl implements PeripheralService<Ups> {

    @Autowired
    PeripheralDAO<Ups> upsDAOImpl;

    @Transactional
    @Override
    public void save(Ups ups) throws EntityExistsException {
        for(Ups u : listAll()) {
            if (Objects.equals(u, ups)) {
                throw new EntityExistsException("An "+ ups.toString() +" already exists!");
            }
        }
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
    public Ups findById(long id) throws EntityNotFoundException {

        if (upsDAOImpl.findById(id) == null) {
            throw new EntityNotFoundException("An ups with " + id + " not found!");
        }
        return upsDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Ups> listAll() {
        return upsDAOImpl.listAll();
    }
}

