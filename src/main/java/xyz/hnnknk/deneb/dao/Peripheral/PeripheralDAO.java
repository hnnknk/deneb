package xyz.hnnknk.deneb.dao.Peripheral;

import xyz.hnnknk.deneb.model.Peripheral;

import java.util.List;

public interface PeripheralDAO<T extends Peripheral> {

    void save(T t);
    void update(T t);
    void delete(long id);

    T findById(long id);

    List<T> listAll();

    boolean isExists(T t);
}
