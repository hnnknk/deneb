package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.SystemUnit;
import xyz.hnnknk.deneb.model.PowerSupply;

import java.util.List;

@Service
public class PowerSupplyServiceImpl implements SystemUnitService {

    @Autowired
    SystemUnitDAO powerSupplyDAOImpl;

    @Transactional
    @Override
    public void save(SystemUnit systemUnit) throws EntityExistsException {
        for(PowerSupply r : listAll()) {
            if (r.getId().equals(systemUnit.getId())) {
                throw new EntityExistsException("A powerSupply with invNumber "+ systemUnit.getId() +" already exists!");
            }
        }
        powerSupplyDAOImpl.save(systemUnit);
    }

    @Transactional
    @Override
    public void update(SystemUnit systemUnit) {
        powerSupplyDAOImpl.update(systemUnit);
    }

    @Transactional
    @Override
    public void delete(long id) {
        powerSupplyDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public PowerSupply findById(long id) throws EntityNotFoundException {

        if(powerSupplyDAOImpl.findById(id) == null) {
            throw new EntityNotFoundException("A powerSupply with " + id + " not found!");
        }
        return (PowerSupply) powerSupplyDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<PowerSupply> listAll() {
        return powerSupplyDAOImpl.listAll();
    }
}

