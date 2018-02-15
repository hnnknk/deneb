package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.SystemUnit;
import xyz.hnnknk.deneb.model.MotherBoard;

import java.util.List;

@Service
public class MotherBoardServiceImpl implements SystemUnitService {

    @Autowired
    SystemUnitDAO motherBoardDAOImpl;

    @Transactional
    @Override
    public void save(SystemUnit systemUnit) throws EntityExistsException {
        for(MotherBoard r : listAll()) {
            if (r.getId().equals(systemUnit.getId())) {
                throw new EntityExistsException("A motherBoard with invNumber "+ systemUnit.getId() +" already exists!");
            }
        }
        motherBoardDAOImpl.save(systemUnit);
    }

    @Transactional
    @Override
    public void update(SystemUnit systemUnit) {
        motherBoardDAOImpl.update(systemUnit);
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
        return (MotherBoard) motherBoardDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<MotherBoard> listAll() {
        return motherBoardDAOImpl.listAll();
    }
}

