package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.KeyboardDAO;
import xyz.hnnknk.deneb.model.Keyboard;

import java.util.List;

@Service
public class KeyboardServiceImpl implements KeyboardService {

    @Autowired
    KeyboardDAO keyboardDAO;

    @Transactional
    @Override
    public void save(Keyboard keyboard) {
        keyboardDAO.save(keyboard);
    }

    @Transactional
    @Override
    public void update(Keyboard keyboard) {
        keyboardDAO.update(keyboard);
    }

    @Transactional
    @Override
    public void delete(long id) {
        keyboardDAO.delete(id);
    }

    @Transactional
    @Override
    public Keyboard findById(long id) {
        return keyboardDAO.findById(id);
    }

    @Transactional
    @Override
    public List<Keyboard> listAllKeyboards() {
        return keyboardDAO.listAllKeyboards();
    }

    @Transactional
    @Override
    public boolean isKeyboardExists(Keyboard keyboard) {
        return keyboardDAO.isKeyboardExists(keyboard);
    }
}
