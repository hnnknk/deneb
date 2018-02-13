package xyz.hnnknk.deneb.service.Peripheral;

import xyz.hnnknk.deneb.model.Peripheral;

import java.util.List;

public interface PeripheralService<T extends Peripheral> {

    void save(T t);
    void update(T t);
    void delete(long id);

    T findById(long id);

    List<T> listAll();

    boolean isExists(T t);
}
