package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.model.PowerSupply;

import java.util.List;

public interface PowerSupplyService {

    void save(PowerSupply powerSupply);
    void update(PowerSupply powerSupply);
    void delete(long id);

    PowerSupply findById(long id);

    List<PowerSupply> listAllPowerSupplies();

    boolean isPowerSupplyExists(PowerSupply powerSupply);
}
