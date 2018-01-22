package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.model.Mouse;

import java.util.List;

public interface MouseService {

    void save(Mouse mouse);
    void update(Mouse mouse);
    void delete(long id);

    Mouse findById(long id);

    List<Mouse> listAllMouses();
}
