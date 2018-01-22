package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.Ups;

import java.util.List;

public interface UpsDAO {

    void save(Ups ups);
    void update(Ups ups);
    void delete(long id);

    Ups findById(long id);

    List<Ups> listAllUpses();

    boolean isUpsExists(Ups ups);
}
