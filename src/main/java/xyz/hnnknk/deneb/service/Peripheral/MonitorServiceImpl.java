package xyz.hnnknk.deneb.service.Peripheral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.Peripheral.PeripheralDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Monitor;

import java.util.List;
import java.util.Objects;

@Service
public class MonitorServiceImpl implements PeripheralService<Monitor> {

    @Autowired
    PeripheralDAO<Monitor> monitorDAOImpl;

    @Transactional
    @Override
    public void save(Monitor monitor) throws EntityExistsException {

        for(Monitor mon : listAll()) {
            if (Objects.equals(mon, monitor)) {
                throw new EntityExistsException("A "+ monitor.toString() +" already exists!");
            }
        }
        monitorDAOImpl.save(monitor);
    }

    @Transactional
    @Override
    public void update(Monitor monitor) {
        monitorDAOImpl.update(monitor);
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
        return monitorDAOImpl.findById(id);}

    @Transactional
    @Override
    public List<Monitor> listAll() { return monitorDAOImpl.listAll(); }
}
