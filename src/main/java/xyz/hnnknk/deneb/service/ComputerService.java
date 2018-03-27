package xyz.hnnknk.deneb.service;

import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Computer;

import java.util.List;

public interface ComputerService {

    void save(Computer computer) throws EntityExistsException;
    void update(Computer computer);
    void delete(long id);

    Computer findById(long id) throws EntityNotFoundException;

    List<Computer> listAll();
}
