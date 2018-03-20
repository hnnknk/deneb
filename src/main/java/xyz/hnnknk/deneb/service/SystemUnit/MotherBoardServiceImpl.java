package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.MotherBoard;

import java.util.List;
import java.util.Objects;

@Service
public class MotherBoardServiceImpl implements SystemUnitService<MotherBoard> {

    @Autowired
    SystemUnitDAO<MotherBoard> motherBoardDAOImpl;

    @Transactional
    @Override
    public void save(MotherBoard motherBoard) throws EntityExistsException {
        for(MotherBoard r : listAll()) {
            if (Objects.equals(r, motherBoard)) {
                throw new EntityExistsException("A "+ motherBoard.toString() +" already exists!");
            }
        }
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
    public MotherBoard findById(long id) throws EntityNotFoundException {

        if(motherBoardDAOImpl.findById(id) == null) {
            throw new EntityNotFoundException("A motherBoard with " + id + " not found!");
        }

        return motherBoardDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<MotherBoard> listAll() {
        return motherBoardDAOImpl.listAll();
    }
}

