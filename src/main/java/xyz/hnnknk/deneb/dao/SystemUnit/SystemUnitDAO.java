package xyz.hnnknk.deneb.dao.SystemUnit;

import xyz.hnnknk.deneb.model.SystemUnit;

import java.util.List;

public interface SystemUnitDAO<T extends SystemUnit> {

    void save(T t);
    void update(T t);
    void delete(long id);

    T findById(long id);

    List<T> listAll();

    boolean isExists(T t);
}
