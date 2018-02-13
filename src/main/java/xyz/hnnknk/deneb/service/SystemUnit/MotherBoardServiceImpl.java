package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.model.MotherBoard;
import xyz.hnnknk.deneb.model.SystemUnit;

import java.util.List;

@Service
public class MotherBoardServiceImpl implements SystemUnitService{

    @Autowired
    SystemUnitDAO motherBoardDAOImpl;

    @Transactional
    @Override
    public void save(SystemUnit systemUnit) {
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
    public MotherBoard findById(long id) {
        return (MotherBoard) motherBoardDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<MotherBoard> listAll() {
        return motherBoardDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isExists(SystemUnit systemUnit) {
        boolean result = false;

        for(MotherBoard m : listAll()) {
            if (m.getId().equals(systemUnit.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
