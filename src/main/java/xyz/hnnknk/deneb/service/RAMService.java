package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.model.RAM;

import java.util.List;

public interface RAMService {

    void save(RAM ram);
    void update(RAM ram);
    void delete(long id);

    RAM findById(long id);

    List<RAM> listAllRams();

    boolean isRamExists(RAM ram);
}
