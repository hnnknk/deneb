package xyz.hnnknk.deneb.service.Peripheral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.model.Monitor;
import xyz.hnnknk.deneb.model.Peripheral;

import java.util.List;

@Service
public class MonitorServiceImpl implements PeripheralService {

    @Autowired
    PeripheralDAO monitorDAOImpl;

    @Transactional
    @Override
    public void save(Peripheral peripheral) {
        monitorDAOImpl.save(peripheral);
    }

    @Transactional
    @Override
    public void update(Peripheral peripheral) {
        monitorDAOImpl.update(peripheral);
    }

    @Transactional
    @Override
    public void delete(long id) {
        monitorDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public Monitor findById(long id) { return (Monitor) monitorDAOImpl.findById(id);}

    @Transactional
    @Override
    public List<Monitor> listAll() { return monitorDAOImpl.listAll(); }

    @Transactional
    @Override
    public boolean isExists(Peripheral peripheral) {

        boolean result = false;

        for(Monitor mon : listAll()) {
            if (mon.getInvNumber().equals(peripheral.getInvNumber())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
