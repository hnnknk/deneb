package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.PeripheralDAO;
import xyz.hnnknk.deneb.model.Monitor;

import java.util.List;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    PeripheralDAO monitorDAOImpl;

    @Transactional
    @Override
    public void save(Monitor monitor) {
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
    public Monitor findById(long id) {
        return (Monitor) monitorDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Monitor> listAllMonitors() {
        return monitorDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isMonitorExists(Monitor monitor) {
        return monitorDAOImpl.isExists(monitor);
    }
}
