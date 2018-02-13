package xyz.hnnknk.deneb.service.Peripheral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.model.Keyboard;
import xyz.hnnknk.deneb.model.Peripheral;

import java.util.List;

@Service
public class KeyboardServiceImpl implements PeripheralService {

    @Autowired
    PeripheralDAO keyboardDAOImpl;

    @Transactional
    @Override
    public void save(Peripheral peripheral) {
        keyboardDAOImpl.save(peripheral);
    }

    @Transactional
    @Override
    public void update(Peripheral peripheral) {
        keyboardDAOImpl.update(peripheral);
    }

    @Transactional
    @Override
    public void delete(long id) {
        keyboardDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public Keyboard findById(long id) { return (Keyboard) keyboardDAOImpl.findById(id); }

    @Transactional
    @Override
    public List<Keyboard> listAll() {
        return keyboardDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isExists(Peripheral peripheral) {
        boolean result = false;

        for(Keyboard k : listAll()) {
            if (k.getInvNumber().equals(peripheral.getInvNumber())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
