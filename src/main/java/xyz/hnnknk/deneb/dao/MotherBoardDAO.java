package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.MotherBoard;

import java.util.List;

public interface MotherBoardDAO {

    void save(MotherBoard motherBoard);
    void update(MotherBoard motherBoard);
    void delete(long id);

    MotherBoard findById(long id);

    List<MotherBoard> listAllMotherBoards();

    boolean isMotherBoardExists(MotherBoard motherBoard);
}
