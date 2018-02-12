package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.PowerSupplyDAO;
import xyz.hnnknk.deneb.model.PowerSupply;

import java.util.List;

public class PowerSupplyServiceImpl implements PowerSupplyService {

    @Autowired
    PowerSupplyDAO powerSupplyDAO;

    @Transactional
    @Override
    public void save(PowerSupply powerSupply) {
        powerSupplyDAO.save(powerSupply);
    }

    @Transactional
    @Override
    public void update(PowerSupply powerSupply) {
        powerSupplyDAO.update(powerSupply);
    }

    @Transactional
    @Override
    public void delete(long id) {
        powerSupplyDAO.delete(id);
    }

    @Transactional
    @Override
    public PowerSupply findById(long id) {
        return powerSupplyDAO.findById(id);
    }

    @Transactional
    @Override
    public List<PowerSupply> listAllPowerSupplies() {
        return powerSupplyDAO.listAllPowerSupplies();
    }

    @Transactional
    @Override
    public boolean isPowerSupplyExists(PowerSupply powerSupply) {
        return powerSupplyDAO.isPowerSupplyExists(powerSupply);
    }
}
