package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnitDAO;
import xyz.hnnknk.deneb.model.MotherBoard;

import java.util.List;

@Service
public class MotherBoardServiceImpl implements MotherBoardService{

    @Autowired
    SystemUnitDAO motherBoardDAOImpl;

    @Transactional
    @Override
    public void save(MotherBoard motherBoard) {
        motherBoardDAOImpl.save(motherBoard);
    }

    @Transactional
    @Override
    public void update(MotherBoard motherBoard) {
        motherBoardDAOImpl.update(motherBoard);
    }

    @Transactional
    @Override
    public void delete(long id) {
        motherBoardDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public MotherBoard findById(long id) {
        return (MotherBoard) motherBoardDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<MotherBoard> listAllMotherBoards() {
        return motherBoardDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isMotherBoardExists(MotherBoard motherBoard) {
        return motherBoardDAOImpl.isExists(motherBoard);
    }
}
