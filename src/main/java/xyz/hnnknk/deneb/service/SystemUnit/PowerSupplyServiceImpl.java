package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.model.PowerSupply;
import xyz.hnnknk.deneb.model.SystemUnit;

import java.util.List;

@Service
public class PowerSupplyServiceImpl implements SystemUnitService {

    @Autowired
    SystemUnitDAO powerSupplyDAOImpl;

    @Transactional
    @Override
    public void save(SystemUnit systemUnit) {
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
    public PowerSupply findById(long id) {
        return (PowerSupply) powerSupplyDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<PowerSupply> listAll() {
        return powerSupplyDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isExists(SystemUnit systemUnit) {

        boolean result = false;

        for(PowerSupply pow : listAll()) {
            if (pow.getId().equals(systemUnit.getId())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
