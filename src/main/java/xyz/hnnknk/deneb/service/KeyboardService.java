package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.model.Keyboard;

import java.util.List;

public interface KeyboardService {

    void save(Keyboard keyboard);
    void update(Keyboard keyboard);
    void delete(long id);

    Keyboard findById(long id);

    List<Keyboard> listAllKeyboards();
}
