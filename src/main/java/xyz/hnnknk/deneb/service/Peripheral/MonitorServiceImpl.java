package xyz.hnnknk.deneb.service.Peripheral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Monitor;
import xyz.hnnknk.deneb.model.Peripheral;

import java.util.List;

@Service
public class MonitorServiceImpl implements PeripheralService {

    @Autowired
    PeripheralDAO monitorDAOImpl;

    @Transactional
    @Override
    public void save(Peripheral peripheral) throws EntityExistsException {

        for(Monitor mon : listAll()) {
            if (mon.getInvNumber().equals(peripheral.getInvNumber())) {
                throw new EntityExistsException("A monitor with invNumber "+ peripheral.getInvNumber() +" already exists!");
            }
        }
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
    public Monitor findById(long id) throws EntityNotFoundException {

        if(monitorDAOImpl.findById(id) == null) {
            throw new EntityNotFoundException("A monitor with " + id + " not found!");
        }
        return (Monitor) monitorDAOImpl.findById(id);}

    @Transactional
    @Override
    public List<Monitor> listAll() { return monitorDAOImpl.listAll(); }
}
