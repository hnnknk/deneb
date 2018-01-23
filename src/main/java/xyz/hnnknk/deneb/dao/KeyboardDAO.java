package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.Keyboard;

import java.util.List;

public interface KeyboardDAO {

    void save(Keyboard keyboard);
    void update(Keyboard keyboard);
    void delete(long id);

    Keyboard findById(long id);

    List<Keyboard> listAllKeyboards();

    boolean isKeyboardExists(Keyboard keyboard);
}
