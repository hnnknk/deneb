package xyz.hnnknk.deneb.service.Peripheral;

import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Peripheral;

import java.util.List;

public interface PeripheralService<T extends Peripheral> {

    void save(T t) throws EntityExistsException;
    void update(T t);
    void delete(long id);

    T findById(long id) throws EntityNotFoundException;

    List<T> listAll();
}
