package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.model.Monitor;

import java.util.List;

public interface MonitorService {

    void save(Monitor monitor);
    void update(Monitor monitor);
    void delete(long id);

    Monitor findById(long id);

    List<Monitor> listAllMonitors();
}
