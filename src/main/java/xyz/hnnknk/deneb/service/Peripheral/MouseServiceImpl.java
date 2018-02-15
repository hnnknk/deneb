package xyz.hnnknk.deneb.service.Peripheral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Mouse;
import xyz.hnnknk.deneb.model.Peripheral;

import java.util.List;

@Service
public class MouseServiceImpl implements PeripheralService {

    @Autowired
    PeripheralDAO mouseDAOImpl;

    @Transactional
    @Override
    public void save(Peripheral peripheral) throws EntityExistsException {

        for (Mouse m : listAll()) {
            if (m.getInvNumber().equals(peripheral.getInvNumber())) {
                throw new EntityExistsException("A mouse with invNumber "+ peripheral.getInvNumber() +" already exists!");
            }
        }
        mouseDAOImpl.save(peripheral);
    }

    @Transactional
    @Override
    public void update(Peripheral peripheral) {
        mouseDAOImpl.update(peripheral);
    }

    @Transactional
    @Override
    public void delete(long id) {
        mouseDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public Mouse findById(long id) throws EntityNotFoundException {

        if(mouseDAOImpl.findById(id) == null) {
            throw new EntityNotFoundException("A mouse with " + id + " not found!");
        }
        return (Mouse) mouseDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Mouse> listAll() {
        return mouseDAOImpl.listAll();
    }
}
