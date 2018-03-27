package xyz.hnnknk.deneb.dao;

import xyz.hnnknk.deneb.model.Computer;

import java.util.List;

public interface ComputerDAO {

    void save(Computer computer);
    void update(Computer computer);
    void delete(long id);

    Computer findById(long id);

    List<Computer> listAll();
}
