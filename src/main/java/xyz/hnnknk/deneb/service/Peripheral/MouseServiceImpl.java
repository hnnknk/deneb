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
import java.util.Objects;

@Service
public class MouseServiceImpl implements PeripheralService<Mouse> {

    @Autowired
    PeripheralDAO<Mouse> mouseDAOImpl;

    @Transactional
    @Override
    public void save(Mouse mouse) throws EntityExistsException {

        for (Mouse m : listAll()) {
            if (Objects.equals(m, mouse)) {
                throw new EntityExistsException("A "+ mouse.toString() +" already exists!");
            }
        }
        mouseDAOImpl.save(mouse);
    }

    @Transactional
    @Override
    public void update(Mouse mouse) {
        mouseDAOImpl.update(mouse);
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
        return mouseDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Mouse> listAll() {
        return mouseDAOImpl.listAll();
    }
}
