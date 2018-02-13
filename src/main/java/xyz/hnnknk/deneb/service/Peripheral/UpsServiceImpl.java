package xyz.hnnknk.deneb.service.Peripheral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.model.Peripheral;
import xyz.hnnknk.deneb.model.Ups;

import java.util.List;

@Service
public class UpsServiceImpl implements PeripheralService {

    @Autowired
    PeripheralDAO upsDAOImpl;

    @Transactional
    @Override
    public void save(Peripheral peripheral) {
        upsDAOImpl.save(peripheral);
    }

    @Transactional
    @Override
    public void update(Peripheral peripheral) {
        upsDAOImpl.update(peripheral);
    }

    @Transactional
    @Override
    public void delete(long id) {
        upsDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public Ups findById(long id) {
        return (Ups) upsDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Ups> listAll() {
        return upsDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isExists(Peripheral peripheral) {

        boolean result = false;

        for(Ups u : listAll()) {
            if (u.getInvNumber().equals(peripheral.getInvNumber())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
