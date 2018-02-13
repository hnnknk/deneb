package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnitDAO;
import xyz.hnnknk.deneb.model.PowerSupply;
import xyz.hnnknk.deneb.model.SystemUnit;

import java.util.List;

@Service
public class PowerSupplyServiceImpl implements PowerSupplyService {

    @Autowired
    SystemUnitDAO powerSupplyDAOImpl;

    @Transactional
    @Override
    public void save(PowerSupply powerSupply) {
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
    public PowerSupply findById(long id) {
        return (PowerSupply) powerSupplyDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<PowerSupply> listAllPowerSupplies() {
        return powerSupplyDAOImpl.listAll();
    }

    @Transactional
    @Override
    public boolean isPowerSupplyExists(PowerSupply powerSupply) {
        return powerSupplyDAOImpl.isExists(powerSupply);
    }
}
