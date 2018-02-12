package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.model.MotherBoard;

import java.util.List;

public interface MotherBoardService {

    void save(MotherBoard motherBoard);
    void update(MotherBoard motherBoard);
    void delete(long id);

    MotherBoard findById(long id);

    List<MotherBoard> listAllMotherBoards();

    boolean isMotherBoardExists(MotherBoard motherBoard);
}
