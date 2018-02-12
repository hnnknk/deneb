package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.PowerSupply;

import java.util.List;

public interface PowerSupplyDAO {

    void save(PowerSupply powerSupply);
    void update(PowerSupply powerSupply);
    void delete(long id);

    PowerSupply findById(long id);

    List<PowerSupply> listAllPowerSupplies();

    boolean isPowerSupplyExists(PowerSupply powerSupply);
}
