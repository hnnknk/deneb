package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.PeripheralDAO;
import xyz.hnnknk.deneb.model.Keyboard;

import java.util.List;

@Service
public class KeyboardServiceImpl implements KeyboardService {

    @Autowired
    PeripheralDAO keyboardDAOImpl;

    @Transactional
    @Override
    public void save(Keyboard keyboard) {
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
    public Keyboard findById(long id) {
        return (Keyboard) keyboardDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Keyboard> listAllKeyboards() {
        return keyboardDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isKeyboardExists(Keyboard keyboard) {
        return keyboardDAOImpl.isExists(keyboard);
    }
}
