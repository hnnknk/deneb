package xyz.hnnknk.deneb.service.SystemUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.deneb.dao.SystemUnit.SystemUnitDAO;
import xyz.hnnknk.deneb.exceptions.EntityExistsException;
import xyz.hnnknk.deneb.exceptions.EntityNotFoundException;
import xyz.hnnknk.deneb.model.Processor;

import java.util.List;
import java.util.Objects;

@Service
public class ProcessorServiceImpl implements SystemUnitService<Processor> {

    @Autowired
    SystemUnitDAO<Processor> processorDAOImpl;

    @Transactional
    @Override
    public void save(Processor processor) throws EntityExistsException {
        for(Processor r : listAll()) {
            if (Objects.equals(r, processor)) {
                throw new EntityExistsException("A "+ processor.toString() +" already exists!");
            }
        }
        processorDAOImpl.save(processor);
    }

    @Transactional
    @Override
    public void update(Processor processor) {
        processorDAOImpl.update(processor);
    }

    @Transactional
    @Override
    public void delete(long id) {
        processorDAOImpl.delete(id);
    }

    @Transactional
    @Override
    public Processor findById(long id) throws EntityNotFoundException {

        if(processorDAOImpl.findById(id) == null) {
            throw new EntityNotFoundException("A processor with " + id + " not found!");
        }
        return processorDAOImpl.findById(id);
    }

    @Transactional
    @Override
    public List<Processor> listAll() {
        return processorDAOImpl.listAll();
    }
}

