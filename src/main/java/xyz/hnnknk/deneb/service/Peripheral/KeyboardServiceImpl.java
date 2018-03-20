package xyz.hnnknk.deneb.service.Peripheral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Keyboard;
import xyz.hnnknk.deneb.model.Peripheral;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
public class KeyboardServiceImpl implements PeripheralService<Keyboard> {

    @Autowired
    PeripheralDAO<Keyboard> keyboardDAOImpl;

    @Transactional
    @Override
    public void save(Keyboard keyboard) throws EntityExistsException {

        for(Keyboard k : listAll()) {
            if (Objects.equals(k, keyboard)) {
                throw new EntityExistsException("A " + keyboard.toString() + " already exists!");
            }
        }
        keyboardDAOImpl.save(keyboard);
    }

    @Transactional
    @Override
    public void update(Keyboard keyboard) {
        keyboardDAOImpl.update(keyboard);
    }

    @Transactional
    @Override
    public void delete(long id) {
        keyboardDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public Keyboard findById(long id) throws EntityNotFoundException {

        if(keyboardDAOImpl.findById(id) == null) {
            throw new EntityNotFoundException("A keyboard with " + id + " not found!");
        }
        return keyboardDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Keyboard> listAll() {
        return keyboardDAOImpl.listAll();
    }
}
