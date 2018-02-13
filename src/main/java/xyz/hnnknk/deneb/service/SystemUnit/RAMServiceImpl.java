package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.model.RAM;
import xyz.hnnknk.deneb.model.SystemUnit;

import java.util.List;

@Service
public class RAMServiceImpl implements SystemUnitService {

    @Autowired
    SystemUnitDAO RAMDAOImpl;

    @Transactional
    @Override
    public void save(SystemUnit systemUnit) {
        RAMDAOImpl.save(systemUnit);
    }

    @Transactional
    @Override
    public void update(SystemUnit systemUnit) {
        RAMDAOImpl.update(systemUnit);
    }

    @Transactional
    @Override
    public void delete(long id) {
        RAMDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public RAM findById(long id) {
        return (RAM) RAMDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<RAM> listAll() {
        return RAMDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isExists(SystemUnit systemUnit) {

        boolean result = false;

        for(RAM r : listAll()) {
            if (r.getId().equals(systemUnit.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
