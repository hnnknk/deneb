package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.Mouse;

import java.util.List;

public interface MouseDAO {

    void save(Mouse mouse);
    void update(Mouse mouse);
    void delete(long id);

    Mouse findById(long id);

    List<Mouse> listAllMouses();

    boolean isMouseExists(Mouse mouse);
}
