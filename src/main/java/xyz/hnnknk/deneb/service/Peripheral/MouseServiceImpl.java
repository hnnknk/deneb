package xyz.hnnknk.deneb.service.Peripheral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.model.Mouse;
import xyz.hnnknk.deneb.model.Peripheral;

import java.util.List;

@Service
public class MouseServiceImpl implements PeripheralService {

    @Autowired
    PeripheralDAO mouseDAOImpl;

    @Transactional
    @Override
    public void save(Peripheral peripheral) {
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
    public Mouse findById(long id) {
        return (Mouse) mouseDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Mouse> listAll() {
        return mouseDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isExists(Peripheral peripheral) {
        boolean result = false;

        for (Mouse m : listAll()) {
            if (m.getInvNumber().equals(peripheral.getInvNumber())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
