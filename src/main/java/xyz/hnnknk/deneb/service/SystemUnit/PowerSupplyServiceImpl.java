package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.PowerSupply;

import java.util.List;
import java.util.Objects;

@Service
public class PowerSupplyServiceImpl implements SystemUnitService<PowerSupply> {

    @Autowired
    SystemUnitDAO<PowerSupply> powerSupplyDAOImpl;

    @Transactional
    @Override
    public void save(PowerSupply powerSupply) throws EntityExistsException {
        for(PowerSupply r : listAll()) {
            if (Objects.equals(r, powerSupply)) {
                throw new EntityExistsException("A  "+ powerSupply.toString() +" already exists!");
            }
        }
        powerSupplyDAOImpl.save(powerSupply);
    }

    @Transactional
    @Override
    public void update(PowerSupply powerSupply) {
        powerSupplyDAOImpl.update(powerSupply);
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
        return powerSupplyDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<PowerSupply> listAll() {
        return powerSupplyDAOImpl.listAll();
    }
}

