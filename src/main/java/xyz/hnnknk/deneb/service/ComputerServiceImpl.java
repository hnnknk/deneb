package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.ComputerDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Computer;

import java.util.List;
import java.util.Objects;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    ComputerDAO computerDAO;

    @Transactional
    @Override
    public void save(Computer computer) throws EntityExistsException {
        for(Computer c : listAll()) {
            if (Objects.equals(c, computer)) {
                throw new EntityExistsException("A " + computer.toString() + " already exists!");
            }
        }
        computerDAO.save(computer);
    }

    @Transactional
    @Override
    public void update(Computer computer) {
        computerDAO.update(computer);
    }

    @Transactional
    @Override
    public void delete(long id) {
        computerDAO.delete(id);
    }

    @Transactional
    @Override
    public Computer findById(long id) throws EntityNotFoundException {
        if(computerDAO.findById(id) == null) {
            throw new EntityNotFoundException("A computer with " + id + " not found!");
        }

        return computerDAO.findById(id);
    }

    @Transactional
    @Override
    public List<Computer> listAll() {
        return computerDAO.listAll();
    }
}
