package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.RAM;

import java.util.List;

public interface RAMDAO {

    void save(RAM ram);
    void update(RAM ram);
    void delete(long id);

    RAM findById(long id);

    List<RAM> listAllRams();

    boolean isRamExists(RAM ram);
}
