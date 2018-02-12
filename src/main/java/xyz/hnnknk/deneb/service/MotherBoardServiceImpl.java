package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.MotherBoardDAO;
import xyz.hnnknk.deneb.model.MotherBoard;

import java.util.List;

public class MotherBoardServiceImpl implements MotherBoardService{

    @Autowired
    MotherBoardDAO motherBoardDAO;

    @Transactional
    @Override
    public void save(MotherBoard motherBoard) {
        motherBoardDAO.save(motherBoard);
    }

    @Transactional
    @Override
    public void update(MotherBoard motherBoard) {
        motherBoardDAO.update(motherBoard);
    }

    @Transactional
    @Override
    public void delete(long id) {
        motherBoardDAO.delete(id);
    }

    @Transactional
    @Override
    public MotherBoard findById(long id) {
        return motherBoardDAO.findById(id);
    }

    @Transactional
    @Override
    public List<MotherBoard> listAllMotherBoards() {
        return motherBoardDAO.listAllMotherBoards();
    }

    @Transactional
    @Override
    public boolean isMotherBoardExists(MotherBoard motherBoard) {
        return motherBoardDAO.isMotherBoardExists(motherBoard);
    }
}
