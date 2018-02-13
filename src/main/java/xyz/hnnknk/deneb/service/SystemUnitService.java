package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.model.HDD;
import xyz.hnnknk.deneb.model.SystemUnit;

import java.util.List;

public interface SystemUnitService<T extends SystemUnit> {

    void save(T t);
    void update(T t);
    void delete(long id);

    T findById(long id);

    List<T> listAll();

    boolean isExists(T t);
}
