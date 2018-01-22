package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.MonitorDAO;
import xyz.hnnknk.deneb.model.Monitor;

import java.util.List;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    MonitorDAO monitorDAO;

    @Transactional
    @Override
    public void save(Monitor monitor) {
        monitorDAO.save(monitor);
    }

    @Transactional
    @Override
    public void update(Monitor monitor) {
        monitorDAO.update(monitor);
    }

    @Transactional
    @Override
    public void delete(long id) {
        monitorDAO.delete(id);
    }

    @Transactional
    @Override
    public Monitor findById(long id) {
        return monitorDAO.findById(id);
    }

    @Transactional
    @Override
    public List<Monitor> listAllMonitors() {
        return monitorDAO.listAllMonitors();
    }

    @Transactional
    @Override
    public boolean isMonitorExists(Monitor monitor) {
        return monitorDAO.isMonitorExists(monitor);
    }
}
