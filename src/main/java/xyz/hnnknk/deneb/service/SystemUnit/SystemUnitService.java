package xyz.hnnknk.deneb.service.SystemUnit;

import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.SystemUnit;

import java.util.List;

public interface SystemUnitService<T extends SystemUnit> {

    void save(T t) throws EntityExistsException;
    void update(T t);
    void delete(long id);

    T findById(long id) throws EntityNotFoundException;

    List<T> listAll();
}
