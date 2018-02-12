package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.model.HDD;

import java.util.List;

public interface HDDService {

    void save(HDD hdd);
    void update(HDD hdd);
    void delete(long id);

    HDD findById(long id);

    List<HDD> listAllHdds();

    boolean isHddExists(HDD hdd);
}
