package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.model.HDD;
import xyz.hnnknk.deneb.model.SystemUnit;

import java.util.List;

@Service
public class HDDServiceImpl implements SystemUnitService {

    @Autowired
    SystemUnitDAO HDDDAOImpl;

    @Transactional
    @Override
    public void save(SystemUnit systemUnit) {
        HDDDAOImpl.save(systemUnit);
    }

    @Transactional
    @Override
    public void update(SystemUnit systemUnit) {
        HDDDAOImpl.update(systemUnit);
    }

    @Transactional
    @Override
    public void delete(long id) {
        HDDDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public HDD findById(long id) {
        return (HDD) HDDDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<HDD> listAll() {
        return HDDDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isExists(SystemUnit systemUnit) {
        return HDDDAOImpl.isExists(systemUnit);
    }
}
